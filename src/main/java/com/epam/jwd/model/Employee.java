package com.epam.jwd.model;

public class Employee extends User implements Comparable<Employee> {

    private static final long serialVersionUID = 4137845587730884749L;

    /**
     * Mutable field, should either implement Serializable for proper
     * serialization or have modifier transient to forbid serialization
     * completely.
     */
    private final Department department;

    public Employee(Integer id, String name, int age, Department department) {
        super(id, name, age);
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", department=" + department +
                '}';
    }

    @Override
    public int compareTo(Employee o) {
        return this.getName().compareTo(o.getName());
    }
}
