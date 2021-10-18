package com.epam.jwd.web.db;

import java.sql.Connection;

public class TransactionConnectionPool implements ConnectionPool {

    private ConnectionPool connectionPool;

    @Override
    public boolean isInitialized() {
        return connectionPool.isInitialized();
    }

    @Override
    public boolean init() throws CouldNotInitializeConnectionPoolError {
        return connectionPool.init();
    }

    @Override
    public boolean shutDown() {
        return connectionPool.shutDown();
    }

    @Override
    public Connection takeConnection() throws InterruptedException {
        return connectionPool.takeConnection();
    }

    @Override
    public void returnConnection(Connection connection) {
        connectionPool.returnConnection(connection);
    }
}
