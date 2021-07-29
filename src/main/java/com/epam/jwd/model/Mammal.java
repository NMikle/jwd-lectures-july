package com.epam.jwd.model;

public interface Mammal {

    int MINIMAL_AGE = 0;

    void move();

    String getName();

    int getAge();

    default void defaultMethodExample() {
        System.out.println("Default method in interface");
    }

    static void staticMethodExample() {
        //do something
    }

}
