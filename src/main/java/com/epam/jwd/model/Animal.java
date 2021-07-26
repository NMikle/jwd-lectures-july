package com.epam.jwd.model;

import java.util.Objects;

public class Animal {

    private final String name;
    private final int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public final String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return age == animal.age && Objects.equals(name, animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
