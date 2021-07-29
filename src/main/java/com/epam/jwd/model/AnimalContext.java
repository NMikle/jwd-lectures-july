package com.epam.jwd.model;

import java.util.Objects;

public class AnimalContext implements MammalContext {

    private final String type;
    private final String name;
    private final int age;
    private final Integer tailLength;
    private final Tail tail;

    AnimalContext(String type, String name, int age, Integer tailLength, Tail tail) {
        Objects.requireNonNull(type);
        Objects.requireNonNull(name);
        Objects.requireNonNull(age);
        this.type = type;
        this.name = name;
        this.age = age;
        this.tailLength = tailLength;
        this.tail = tail;
    }


    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public Integer getTailLength() {
        return tailLength;
    }

    @Override
    public Tail getTail() {
        return tail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalContext that = (AnimalContext) o;
        return age == that.age && Objects.equals(type, that.type)
                && Objects.equals(name, that.name)
                && Objects.equals(tailLength, that.tailLength)
                && Objects.equals(tail, that.tail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, age, tailLength, tail);
    }

    @Override
    public String toString() {
        return "AnimalContext{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", tailLength=" + tailLength +
                ", tail=" + tail +
                '}';
    }
}
