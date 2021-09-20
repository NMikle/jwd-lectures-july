package com.epam.jwd.app;

import com.epam.jwd.concurrency.NicelySynchronizedObject;
import com.epam.jwd.concurrency.RaceCounter;
import com.epam.jwd.concurrency.StaleValue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.IntStream;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);

    static int x = 0;
    static int y = 0;
    static int a = 0;
    static int b = 0;

    public static void main(String[] args) throws InterruptedException {
//        todo: Simple example:
//        final MathSquare twoSquare = new MathSquare(2);
//        final MathSquare threeSquare = new MathSquare(3);
//        twoSquare.start();
//        threeSquare.start();
//        twoSquare.join();
//        threeSquare.join();
//        LOG.info("Two squared: {}. Three squared: {}", twoSquare, threeSquare);

//        todo: Race Condition
//        RaceCounter c1 = new RaceCounter();
//        IntStream.range(0, 1_000_000).forEach(i -> c1.increment());
//
//        RaceCounter c2 = new RaceCounter();
//        IntStream.range(0, 1_000_000).parallel().forEach(i -> c2.increment());
//
//        LOG.info("c1: {}; c2: {}", c1.getCount(), c2.getCount());

//        todo: stale values
//        final StaleValue staleValue = new StaleValue();
//        final Thread thread = new Thread(staleValue);
//        thread.start();
//        Thread.sleep(100);
//        staleValue.setSleep(true);
//        LOG.info("program end");

//        todo: reordering
//        final Thread first = new Thread(() -> {
//            a = 1;
//            x = b;
//        });
//
//        final Thread second = new Thread(() -> {
//            b = 1;
//            y = a;
//        });
//        first.start();
//        second.start();
//
//        first.join();
//        second.join();
//        LOG.info("x: {}, y: {}", x, y);

//        todo: immutable is great for concurrency
//        final NicelySynchronizedObject hello = new NicelySynchronizedObject("Hello");
//        hello.getName(); //same result for each thread
    }

}
