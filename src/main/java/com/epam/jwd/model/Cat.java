package com.epam.jwd.model;

public class Cat extends Animal {

    public Cat(String name, int age) {
        super(name, age);
    }

    @Override
    public void makeNoise() {
        System.out.println("Meow");
    }

    @Override
    public void move() {
        System.out.println("Cat moves");
    }
}
