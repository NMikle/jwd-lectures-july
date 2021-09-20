package com.epam.jwd.concurrency;

import java.util.Objects;

public class NicelySynchronizedObject {

    private final String name;

    public NicelySynchronizedObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NicelySynchronizedObject that = (NicelySynchronizedObject) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "NicelySynchronizedObject{" +
                "name='" + name + '\'' +
                '}';
    }
}
