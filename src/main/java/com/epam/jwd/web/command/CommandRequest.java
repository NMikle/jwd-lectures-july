package com.epam.jwd.web.command;

import java.util.Optional;

public interface CommandRequest {

    void addAttributeToJsp(String name, Object attribute); //todo: make adding to jsp better

    String getParameter(String name);

    boolean sessionExists();

    boolean addToSession(String name, Object value);

    Optional<Object> retrieveFromSession(String name);

    void clearSession();

    void createSession();

}
