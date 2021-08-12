package com.epam.jwd.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;

public final class Dog extends Animal implements Externalizable {

    private static final long serialVersionUID = -4850091725485468768L;

    public static int staticField;

    private int tailLength;
    private final Tail tail;

    public Dog() {
        super(null, 0);
        this.tail = new Tail(0);
    }

    Dog(String name, int age, int tailLength, Tail tail) {
        super(name, age);
        this.tailLength = tailLength;
        this.tail = tail;
    }

    public int getTailLength() {
        return tailLength;
    }

    public Tail getTail() {
        return tail.copy();
    }

//    public int getWoolLength() {
//        return tail.getWoolLength();
//    }

    @Override
    public void makeNoise() {
        System.out.println("Bark");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Dog dog = (Dog) o;
        return tailLength == dog.tailLength && Objects.equals(tail, dog.tail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tailLength, tail);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", tailLength=" + tailLength +
                ", tail=" + tail +
                ", staticField=" + staticField +
                '}';
    }

    @Override
    public void move() {
        System.out.println("Dog moves");
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.getName());
        out.writeInt(this.getAge());
        out.writeInt(this.getTailLength());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = (String) in.readObject();
        this.age = in.readInt();
        this.tailLength = in.readInt();
    }
}
