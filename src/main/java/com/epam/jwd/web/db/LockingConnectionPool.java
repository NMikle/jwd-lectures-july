package com.epam.jwd.web.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Queue;

public enum LockingConnectionPool implements ConnectionPool {
    INSTANCE;

    private static final Logger LOG = LogManager.getLogger(LockingConnectionPool.class);

    private static final int INITIAL_CONNECTIONS_AMOUNT = 8;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jwd";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    private final Queue<ProxyConnection> availableConnections = new ArrayDeque<>();
    private final List<ProxyConnection> givenAwayConnections = new ArrayList<>();

    private boolean initialized = false;

    @Override
    public synchronized boolean isInitialized() {
        return initialized;
    }

    @Override
    public synchronized boolean init() {
        if (!initialized) {
            initializeConnections(INITIAL_CONNECTIONS_AMOUNT, true);
            initialized = true;
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean shutDown() {
        if (initialized) {
            closeConnections();
            deregisterDrivers();
            initialized = false;
            return true;
        }
        return false;
    }

    @Override
    public synchronized Connection takeConnection() throws InterruptedException {
        while (availableConnections.isEmpty()) {
            this.wait();
        }
        final ProxyConnection connection = availableConnections.poll();
        givenAwayConnections.add(connection);
        return connection;
    }

    @Override
    @SuppressWarnings("SuspiciousMethodCalls")
    public synchronized void returnConnection(Connection connection) {
        if (givenAwayConnections.remove(connection)) {
            availableConnections.add((ProxyConnection) connection);
            this.notifyAll();
        } else {
            LOG.warn("Attempt to add unknown connection to Connection Pool. Connection: {}", connection);
        }
    }

    private void initializeConnections(int amount, boolean failOnConnectionException) {
        try {
            for (int i = 0; i < amount; i++) {
                final Connection conn = DriverManager
                        .getConnection(DB_URL, DB_USER, DB_PASSWORD);
                LOG.info("initialized connection {}", conn);
                final ProxyConnection proxyConnection = new ProxyConnection(conn, this);
                availableConnections.add(proxyConnection);
            }
        } catch (SQLException e) {
            LOG.error("Error occurred creating Connection");
            if (failOnConnectionException) {
                throw new CouldNotInitializeConnectionPoolError("Failed to create Connection", e);
            }
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

    private void closeConnections() {
        closeConnections(this.availableConnections);
        closeConnections(this.givenAwayConnections);
    }

    private void closeConnections(Collection<ProxyConnection> connections) {
        for (ProxyConnection conn : connections) {
            closeConnection(conn);
        }
    }

    private void closeConnection(ProxyConnection conn) {
        try {
            conn.realClose();
            LOG.info("closed connection {}", conn);
        } catch (SQLException e) {
            LOG.error("Could not close connection", e);
        }
    }
}
