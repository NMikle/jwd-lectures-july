package com.epam.jwd.web.model;

import java.util.Arrays;
import java.util.List;

public enum Role {
    USER,
    ADMIN,
    UNAUTHORIZED;

    private static final List<Role> ALL_AVAILABLE_ROLES = Arrays.asList(values());

    public static List<Role> valuesAsList() {
        return ALL_AVAILABLE_ROLES;
    }

}
