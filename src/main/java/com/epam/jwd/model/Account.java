package com.epam.jwd.model;

public interface Account {

    void credit(int amount);

    void debit(int amount);

    int getBalance();

}
