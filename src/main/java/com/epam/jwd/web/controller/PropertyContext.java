package com.epam.jwd.web.controller;

public interface PropertyContext {

    String get(String name);

    static PropertyContext instance() {
        return SimplePropertyContext.getInstance();
    }

}
