package com.epam.jwd.web.db;

import java.sql.Connection;

public interface ConnectionPool {

    static ConnectionPool locking() {
        return LockingConnectionPool.INSTANCE;
    }

    boolean isInitialized();

    boolean init() throws CouldNotInitializeConnectionPoolError;

    boolean shutDown();

    Connection takeConnection() throws InterruptedException;

    void returnConnection(Connection connection);
}
