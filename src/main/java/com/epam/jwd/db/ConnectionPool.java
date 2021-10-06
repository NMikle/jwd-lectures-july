package com.epam.jwd.db;

import java.sql.Connection;

public interface ConnectionPool {

    boolean isInitialized() throws CouldNotInitializeConnectionPool;

    boolean init();

    boolean shutDown();

    Connection takeConnection() throws InterruptedException;

    void returnConnection(Connection connection);

    static ConnectionPool locking() {
        return LockingConnectionPool.INSTANCE;
    }

}
