package com.epam.jwd.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Employee extends User {

    private static final long serialVersionUID = 4137845587730884749L;

    /**
     * Mutable field, should either implement Serializable for proper
     * serialization or have modifier transient to forbid serialization
     * completely.
     */
    private transient Tail tail;

    public Employee(Integer id, String name, int age, Tail tail) {
        super(id, name, age);
        System.out.println("employee constructor");
        this.tail = tail;
    }

    public Tail getTail() {
        return tail;
    }

    private void initializeTransientFields() {
        this.tail = new Tail(0);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        System.out.println("Hey during serialization");
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        initializeTransientFields();
        System.out.println("Hey during deserialization");
    }

    @Override
    public User clone() {
        final Employee employee = (Employee) super.clone();
        employee.tail = this.tail.clone();
        return employee;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", tail=" + tail +
                '}';
    }
}
