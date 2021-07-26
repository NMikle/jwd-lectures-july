package com.epam.jwd.app;

import com.epam.jwd.model.Dog;
import com.epam.jwd.model.Tail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);
    private static final int DEFAULT_WOOL_LENGTH = 1;
    private static final String TEST_DOG_NAME = "Bobik";
    private static final int TEST_DOG_AGE = 2;
    private static final int TEST_DOG_TAIL_LENGTH = 3;
    private static final int CUSTOM_TAIL_WOOL_LENGTH_FOR_TEST = 2;

    public static void main(String[] args) {
        final Dog bobik = new Dog(TEST_DOG_NAME, TEST_DOG_AGE,
                TEST_DOG_TAIL_LENGTH, new Tail(CUSTOM_TAIL_WOOL_LENGTH_FOR_TEST));
        changeDog(bobik);
        System.out.println(bobik);
//        bobik = new Dog("", 1, 1, new Tail(1)); //does not work
//        final int a = 3;
//        a++; //does not work
    }

    private static void changeDog(final Dog dog) {
        dog.getTail().setWoolLength(DEFAULT_WOOL_LENGTH);
//        dog = new Dog("", 1, 1, new Tail(1)); //does not work
    }
}