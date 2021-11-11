package com.epam.jwd.mds.api.model;

public record Employee(Long id, String name, Department department) implements Entity {
    public Employee(String name, Department department) {
        this(null, name, department);
    }
}
