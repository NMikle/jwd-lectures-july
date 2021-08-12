package com.epam.jwd.model;

import java.io.Serializable;
import java.util.Objects;

public class User implements Cloneable, Serializable, Entity {

    private static final long serialVersionUID = -4985231161529339713L;

    private final static int MINIMAL_AGE = 0;

    private final Integer id;
    private final String name;
    private final int age;

    public User(Integer id, String name, int age) {
        System.out.println("user constructor");
        if (id != null && id <= 0) {
            throw new IllegalArgumentException("id should not be negative");
        }
        if (age < MINIMAL_AGE) {
            throw new IllegalArgumentException("age should not be negative");
        }
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public User withId(Integer id) {
        return new User(id, this.name, this.age);
    }

    public static User createUser(String name, int age) {
        return new User(null, name, age);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(id, user.id) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public User clone() {
        try {
            return (User) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace(); //todo: logging
            return null;
        }
    }
}
