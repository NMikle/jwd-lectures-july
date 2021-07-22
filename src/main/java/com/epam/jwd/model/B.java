package com.epam.jwd.model;

public class B extends A {

    static {
        System.out.println("static B");
    }

    {
        System.out.println("instance B");
    }

    public B() {
        System.out.println("constructor B");
    }

}
