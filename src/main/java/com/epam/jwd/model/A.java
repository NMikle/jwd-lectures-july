package com.epam.jwd.model;

public class A {

    static {
        System.out.println("static A");
    }

    {
        System.out.println("instance A");
    }

    public A() {
        System.out.println("constructor A");
    }
}
