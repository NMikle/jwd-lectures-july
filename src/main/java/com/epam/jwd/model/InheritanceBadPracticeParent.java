package com.epam.jwd.model;

public class InheritanceBadPracticeParent {

    private final String field;

    public InheritanceBadPracticeParent(String field) {
        testPublicMethod();//todo: remove public method call from constructor
        this.field = field;
    }

    public void testPublicMethod() { //bad practice
        System.out.println("test method called");
    }

    public String getField() {
        return field;
    }
}
