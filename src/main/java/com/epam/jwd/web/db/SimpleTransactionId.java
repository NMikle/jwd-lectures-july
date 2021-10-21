package com.epam.jwd.web.db;

import java.sql.Connection;
import java.util.Objects;

public class SimpleTransactionId implements TransactionId {

    private final Connection connection;

    public SimpleTransactionId(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleTransactionId that = (SimpleTransactionId) o;
        return Objects.equals(connection, that.connection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connection);
    }

    @Override
    public String toString() {
        return "SimpleTransactionId{" +
                "connection=" + connection +
                '}';
    }
}
