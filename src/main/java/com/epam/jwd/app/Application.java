package com.epam.jwd.app;

import com.epam.jwd.exception.UserNotFoundException;
import com.epam.jwd.model.User;
import com.epam.jwd.model.UserName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);

    private static final String DB_URL = "jdbc:mysql://localhost:3306/jwd";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    private static final String SELECT_ALL_SQL = "select id as id," +
            " first_name as f_name, last_name as l_name, age as" +
            " age, email as email from jwd_user";
    private static final String ID_COLUMN_NAME = "id";
    private static final String FIRST_NAME_COLUMN_NAME = "f_name";
    private static final String LAST_NAME_COLUMN_NAME = "l_name";
    private static final String AGE_COLUMN_NAME = "age";
    private static final String EMAIL_COLUMN_NAME = "email";

    private static final String NOT_FOUND_MSG = "could not extract user";

    public static void main(String[] args) {
        LOG.trace("program start");
        registerDrivers();
        final List<User> users = fetchUsersFromDb();
        users.forEach(user -> LOG.info("found user {}", user));
        deregisterDrivers();
        LOG.trace("program end");
    }

    private static void registerDrivers() {
        LOG.trace("registering sql drivers");
        try {
            DriverManager.registerDriver(DriverManager.getDriver(DB_URL));
        } catch (SQLException e) {
            LOG.error("could not register drivers", e);
            throw new IllegalStateException("Unsuccessful db driver registration attempt");
        }
    }

    private static void deregisterDrivers() {
        LOG.trace("unregistering sql drivers");
        final Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException e) {
                LOG.error("could not deregister driver", e);
            }
        }
    }

    private static List<User> fetchUsersFromDb() {
        try (final Connection connection = DriverManager
                .getConnection(DB_URL, DB_USER, DB_PASSWORD);
             final Statement statement = connection.createStatement();
             final ResultSet resultSet = statement.executeQuery(SELECT_ALL_SQL)) {
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                final User user = extractUser(resultSet)
                        .orElseThrow(() -> new UserNotFoundException(NOT_FOUND_MSG));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            LOG.error("sql error occurred working with users", e);
        } catch (UserNotFoundException e) {
            LOG.error("did not found users", e);
        }
        return Collections.emptyList();
    }

    private static Optional<User> extractUser(ResultSet resultSet) {
        try {
            return Optional.of(new User(
                    resultSet.getLong(ID_COLUMN_NAME),
                    new UserName(
                            resultSet.getString(FIRST_NAME_COLUMN_NAME),
                            resultSet.getString(LAST_NAME_COLUMN_NAME)
                    ),
                    resultSet.getInt(AGE_COLUMN_NAME),
                    resultSet.getString(EMAIL_COLUMN_NAME)
            ));
        } catch (SQLException e) {
            LOG.error("could not extract value from result set", e);
            return Optional.empty();
        }
    }

}
