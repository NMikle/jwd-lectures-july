package com.epam.jwd.web.model;

import java.util.Objects;

import static com.epam.jwd.web.model.Role.USER;

public class User implements Entity {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String password;
    private final Role role;

    public User(Long id, String firstName, String lastName, String password) {
        this(id, firstName, lastName, password, USER);
    }

    public User(Long id, String firstName, String lastName, String password, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id)
                && Objects.equals(firstName, user.firstName)
                && Objects.equals(lastName, user.lastName)
                && Objects.equals(password, user.password)
                && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName,
                lastName, password,
                role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

}
