package com.epam.jwd.model;

import java.io.Serializable;
import java.util.Objects;

public class OldUser implements Serializable, Entity<OldUser> {

    private static final long serialVersionUID = -4985231161529339713L;

    private final static int MINIMAL_AGE = 0;

    private final Integer id;
    private final String name;
    private final int age;

    public OldUser(Integer id, String name, int age) {
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

    public OldUser withId(Integer id) {
        return new OldUser(id, this.name, this.age);
    }

    public static OldUser createUser(String name, int age) {
        return new OldUser(null, name, age);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OldUser oldUser = (OldUser) o;
        return age == oldUser.age && Objects.equals(id, oldUser.id) && Objects.equals(name, oldUser.name);
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
}
