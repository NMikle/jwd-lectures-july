package com.epam.jwd.web.dao;

import com.epam.jwd.web.db.ConnectionPool;
import com.epam.jwd.web.model.Role;
import com.epam.jwd.web.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

public final class MethodUserDao extends CommonDao<User> implements UserDao {

    private static final Logger LOG = LogManager.getLogger(MethodUserDao.class);

    private static final String USER_TABLE_NAME = "jwd_user j join u_role r on r.id = j.role_id";
    private static final String ID_FIELD_NAME = "j.id";
    private static final String EMAIL_FIELD_NAME = "j.email";
    private static final String F_NAME_FIELD_NAME = "j.first_name";
    private static final String L_NAME_FIELD_NAME = "j.last_name";
    private static final String PASSWORD_FIELD_NAME = "j.u_pass";
    private static final String ROLE_FIELD_NAME = "r.r_name";

    private static final List<String> FIELDS = Arrays.asList(
            ID_FIELD_NAME, EMAIL_FIELD_NAME,
            F_NAME_FIELD_NAME, L_NAME_FIELD_NAME,
            PASSWORD_FIELD_NAME, ROLE_FIELD_NAME
    );

    private final String selectByEmailExpression;

    private MethodUserDao(ConnectionPool pool) {
        super(pool, LOG);
        this.selectByEmailExpression = SELECT_ALL_FROM + getTableName() + SPACE + format(WHERE_FIELD, EMAIL_FIELD_NAME);
    }

    @Override
    protected String getTableName() {
        return USER_TABLE_NAME;
    }

    @Override
    protected List<String> getFields() {
        return Collections.emptyList();//todo
    }

    @Override
    protected User extractResult(ResultSet rs) throws SQLException {
        return new User(
                rs.getLong(ID_FIELD_NAME),
                rs.getString(F_NAME_FIELD_NAME),
                rs.getString(L_NAME_FIELD_NAME),
                rs.getString(PASSWORD_FIELD_NAME),
                Role.of(rs.getString(ROLE_FIELD_NAME))
        );
    }

    @Override
    protected void fillEntity(PreparedStatement statement, User entity) throws SQLException {
        //todo
    }

    static UserDao getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Optional<User> readUserByEmail(String email) {
        try {
            return executePreparedForGenericEntity(selectByEmailExpression,
                    this::extractResultCatchingException,
                    st -> st.setString(1, email));
        } catch (InterruptedException e) {
            LOG.info("takeConnection interrupted", e);
            Thread.currentThread().interrupt();
            return Optional.empty();
        }
    }

    private static class Holder {
        public static final UserDao INSTANCE = new MethodUserDao(ConnectionPool.instance());
    }

}
