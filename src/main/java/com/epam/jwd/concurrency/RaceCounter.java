package com.epam.jwd.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceCounter {
//    private int count = 0;
    private final AtomicInteger count = new AtomicInteger(0);


    public void increment() {
        count.incrementAndGet();
//        count++;
    }

    public int getCount() {
        return count.get();
//        return count;
    }
}
