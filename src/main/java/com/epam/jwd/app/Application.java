package com.epam.jwd.app;

import com.epam.jwd.db.EntityExtractionFailedException;
import com.epam.jwd.db.ResultSetExtractor;
import com.epam.jwd.db.StatementPreparator;
import com.epam.jwd.model.DBEntity;
import com.epam.jwd.model.User;
import com.epam.jwd.model.UserName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);

    private static final String DB_URL = "jdbc:mysql://localhost:3306/jwd";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    private static final String SELECT_ALL_SQL = "select id as id," +
            " first_name as f_name, last_name as l_name, age as" +
            " age, email as email from jwd_user";
    private static final String FIND_USERS_OLDER_THAN_SQL = "select id as id," +
            " first_name as f_name, last_name as l_name, age as age," +
            " email as email from jwd_user where age > ?";
    private static final String ID_COLUMN_NAME = "id";
    private static final String FIRST_NAME_COLUMN_NAME = "f_name";
    private static final String LAST_NAME_COLUMN_NAME = "l_name";
    private static final String AGE_COLUMN_NAME = "age";
    private static final String EMAIL_COLUMN_NAME = "email";

    private static final String NOT_FOUND_MSG = "could not extract user";

    public static void main(String[] args) {
        LOG.trace("program start");
        registerDrivers();
//        final List<User> users = fetchUsersOlderThan(30);
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
        return executeStatement(SELECT_ALL_SQL, Application::extractUser);
    }

    private static List<User> fetchUsersOlderThan(int i) {
        return executePrepared(
                FIND_USERS_OLDER_THAN_SQL,
                Application::extractUser,
                st -> st.setInt(1, i)
        );
    }

    private static <T extends DBEntity> List<T> executePrepared(String sql,
                                                                   ResultSetExtractor<T> extractor,
                                                                   StatementPreparator statementPreparation) {
        try (final Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            if (statementPreparation != null) {
                statementPreparation.accept(statement);
            }
            final ResultSet resultSet = statement.executeQuery();
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", sql);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        }
        return Collections.emptyList();
    }

    private static <T extends DBEntity> List<T> executeStatement(String sql,
                                                                 ResultSetExtractor<T> extractor) {
        try (final Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             final Statement statement = connection.createStatement();
             final ResultSet resultSet = statement.executeQuery(sql)) {
            return extractor.extractAll(resultSet);
        } catch (SQLException e) {
            LOG.error("sql exception occurred", e);
            LOG.debug("sql: {}", sql);
        } catch (EntityExtractionFailedException e) {
            LOG.error("could not extract entity", e);
        }
        return Collections.emptyList();
    }


    private static User extractUser(ResultSet resultSet) throws EntityExtractionFailedException {
        try {
            return new User(
                    resultSet.getLong(ID_COLUMN_NAME),
                    new UserName(
                            resultSet.getString(FIRST_NAME_COLUMN_NAME),
                            resultSet.getString(LAST_NAME_COLUMN_NAME)
                    ),
                    resultSet.getInt(AGE_COLUMN_NAME),
                    resultSet.getString(EMAIL_COLUMN_NAME)
            );
        } catch (SQLException e) {
            LOG.error("could not extract value from result set", e);
            throw new EntityExtractionFailedException("failed to extract user");
        }
    }
}
