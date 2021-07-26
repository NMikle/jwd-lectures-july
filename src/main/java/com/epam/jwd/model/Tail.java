package com.epam.jwd.model;

import java.util.Objects;

public class Tail {

    private int woolLength;

    public Tail(int woolLength) {
        this.woolLength = woolLength;
    }

    public int getWoolLength() {
        return woolLength;
    }

    public void setWoolLength(int woolLength) {
        this.woolLength = woolLength;
    }

    public Tail copy() {
        return new Tail(this.woolLength);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tail tail = (Tail) o;
        return woolLength == tail.woolLength;
    }

    @Override
    public int hashCode() {
        return Objects.hash(woolLength);
    }

    @Override
    public String toString() {
        return "Tail{" +
                "woolLength=" + woolLength +
                '}';
    }
}
