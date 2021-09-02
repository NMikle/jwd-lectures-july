package com.epam.jwd.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Consumer;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        final Consumer<String> composedConsumer = test().andThen(x -> {
            System.out.println("second consumer");
            System.out.println(x);
        });
        composedConsumer.accept("Hello");
    }

    private static Consumer<String> test() {
        return x -> {
            System.out.println("first consumer");
            System.out.println(x);
        };
    }

}
