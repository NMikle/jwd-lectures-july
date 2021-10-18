package com.epam.jwd.web.dao;

import com.epam.jwd.web.db.ConnectionPool;
import com.epam.jwd.web.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class MethodUserDao extends CommonDao<User> implements UserDao {

    private static final Logger LOG = LogManager.getLogger(MethodUserDao.class);

    private static final String USER_TABLE_NAME = "jwd_user";
    private static final String ID_FIELD_NAME = "id";
    private static final String F_NAME_FIELD_NAME = "first_name";
    private static final String L_NAME_FIELD_NAME = "last_name";

    private MethodUserDao(ConnectionPool pool) {
        super(pool, LOG);
    }

    @Override
    protected String getTableName() {
        return USER_TABLE_NAME;
    }

    @Override
    protected User extractResult(ResultSet rs) throws SQLException {
        return new User(
                rs.getLong(ID_FIELD_NAME),
                rs.getString(F_NAME_FIELD_NAME),
                rs.getString(L_NAME_FIELD_NAME)
        );
    }

    static UserDao getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        public static final UserDao INSTANCE = new MethodUserDao(ConnectionPool.locking());
    }

}
