package com.epam.jwd.concurrency;

public class RaceCounter {
    private int count = 0;

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
