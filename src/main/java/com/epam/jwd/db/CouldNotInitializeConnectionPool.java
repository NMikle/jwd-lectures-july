package com.epam.jwd.db;

public class CouldNotInitializeConnectionPool extends Error {

    private static final long serialVersionUID = 8553014030613840383L;

    public CouldNotInitializeConnectionPool(String message, Throwable cause) {
        super(message, cause);
    }
}
