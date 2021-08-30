package com.epam.jwd.model;

public class ExceptionFromStaticField {

    public static final ExceptionFromStaticField instance = new ExceptionFromStaticField();

    public ExceptionFromStaticField() {
        throw new RuntimeException("exception from constructor");
    }

}
