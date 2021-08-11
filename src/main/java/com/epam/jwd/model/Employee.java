package com.epam.jwd.model;

public class Employee extends User {

    private Tail tail; //mutable field

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
