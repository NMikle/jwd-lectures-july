package com.epam.jwd.model;

import java.util.Objects;

public class User {

    private final Long id;
    private final UserName name;
    private final int age;
    private final String email;

    public User(Long id, UserName name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public User(UserName name, int age, String email) {
        this(null, name, age, email);
    }

    public Long getId() {
        return id;
    }

    public UserName getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(id, user.id)
                && Objects.equals(name, user.name)
                && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name=" + name +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
