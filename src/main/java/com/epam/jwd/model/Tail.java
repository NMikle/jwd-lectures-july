package com.epam.jwd.model;

import java.io.Serializable;
import java.util.Objects;

public class Tail implements Cloneable, Serializable {

    private static final long serialVersionUID = -4503893919333833279L;

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
    public Tail clone() {
        try {
            return (Tail) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace(); // todo: logging
            return null;
        }
    }

    @Override
    public String toString() {
        return "Tail{" +
                "woolLength=" + woolLength +
                '}';
    }
}
