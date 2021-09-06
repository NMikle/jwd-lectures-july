package com.epam.jwd.model;

public class InheritanceBadPracticeChild extends InheritanceBadPracticeParent {

    public InheritanceBadPracticeChild(String field) {
        super(field);
    }

    @Override
    public void testPublicMethod() {
        System.out.println("final field: " + super.getField());
    }
}
