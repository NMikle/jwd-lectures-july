package com.epam.jwd.web.controller;

public enum PagePaths {
    INDEX("/"),
    MAIN("/WEB-INF/jsp/main.jsp"),
    BIKES("/WEB-INF/jsp/bikes.jsp"),
    LOGIN("/WEB-INF/jsp/login.jsp");

    private final String path;

    PagePaths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public static PagePaths of(String name) {
        for (PagePaths page : values()) {
            if (page.name().equalsIgnoreCase(name)) {
                return page;
            }
        }
        return MAIN;
    }
}
