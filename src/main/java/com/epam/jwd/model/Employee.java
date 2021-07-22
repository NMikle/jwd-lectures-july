package com.epam.jwd.model;

public class Employee extends User {

    public Employee(String name, int age) {
        super(name, age);
    }

    public static User createUser(String name, int age) {
        System.out.println("createUser from Employee");
        return new User(name, age);
    }

}
