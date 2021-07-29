package com.epam.jwd.model;

public interface MammalContext {

    String getType();

    String getName();

    int getAge();

    Integer getTailLength();

    Tail getTail();

    static MammalContext of(String type, String name, int age, Integer tailLength, Tail tail) {
        return new AnimalContext(type, name, age, tailLength, tail);
    }

}
