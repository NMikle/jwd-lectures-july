package com.epam.jwd.model;

public class C extends B {

    static {
        System.out.println("static C");
    }

    {
        System.out.println("instance C");
    }

    public C() {
        System.out.println("constructor C");
    }

}
