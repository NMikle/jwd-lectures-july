package com.epam.jwd.model;

import java.util.Objects;

public final class Dog extends Animal {

    private final int tailLength;
    private final Tail tail;

    Dog(String name, int age, int tailLength, Tail tail) {
        super(name, age);
        this.tailLength = tailLength;
        this.tail = tail;
    }

    public int getTailLength() {
        return tailLength;
    }

    public Tail getTail() {
        return tail.copy();
    }

//    public int getWoolLength() {
//        return tail.getWoolLength();
//    }

    @Override
    public void makeNoise() {
        System.out.println("Bark");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Dog dog = (Dog) o;
        return tailLength == dog.tailLength && Objects.equals(tail, dog.tail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tailLength, tail);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", tailLength=" + tailLength +
                ", tail=" + tail +
                '}';
    }

    @Override
    public void move() {
        System.out.println("Dog moves");
    }
}
