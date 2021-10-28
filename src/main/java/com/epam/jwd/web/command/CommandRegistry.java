package com.epam.jwd.web.command;

import com.epam.jwd.web.model.Role;

import java.util.Arrays;
import java.util.List;

import static com.epam.jwd.web.model.Role.ADMIN;
import static com.epam.jwd.web.model.Role.UNAUTHORIZED;
import static com.epam.jwd.web.model.Role.USER;

public enum CommandRegistry {
    MAIN_PAGE(ShowMainPageCommand.INSTANCE, "main_page"),
    SHOW_BIKES(ShowBikesPageCommand.INSTANCE, "show_bikes"),
    SHOW_USERS(ShowUsersPageCommand.INSTANCE, "show_users", ADMIN),
    SHOW_LOGIN(ShowLoginPageCommand.INSTANCE, "show_login", UNAUTHORIZED),
    ERROR(ShowErrorPageCommand.INSTANCE, "show_error"),
    LOGIN(LoginCommand.INSTANCE, "login", UNAUTHORIZED),
    LOGOUT(LogoutCommand.INSTANCE, "logout", USER, ADMIN),
    DEFAULT(ShowMainPageCommand.INSTANCE, "");

    private final Command command;
    private final String path;
    private final List<Role> allowedRoles;

    CommandRegistry(Command command, String path, Role... roles) {
        this.command = command;
        this.path = path;
        this.allowedRoles = roles != null && roles.length > 0 ? Arrays.asList(roles) : Role.valuesAsList();
    }

    public Command getCommand() {
        return command;
    }

    public List<Role> getAllowedRoles() {
        return allowedRoles;
    }

    static Command of(String name) {
        for (CommandRegistry constant : values()) {
            if (constant.path.equalsIgnoreCase(name)) {
                return constant.command;
            }
        }
        return DEFAULT.command;
    }
}
