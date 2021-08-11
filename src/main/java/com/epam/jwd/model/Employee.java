package com.epam.jwd.model;

public class Employee extends User {

    private static final long serialVersionUID = 4137845587730884749L;

    private Tail tail; //mutable field, should also implement Serializable for proper serialization

    public Employee(Integer id, String name, int age, Tail tail) {
        super(id, name, age);
        System.out.println("employee constructor");
        this.tail = tail;
    }

    public Tail getTail() {
        return tail;
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
