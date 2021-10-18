package com.epam.jwd.web.db;

public interface TransactionManager {

    void initTransaction();

    void commitTransaction();

    boolean isTransactionActive();

    static TransactionManager instance() {
        return null;
    }

}
