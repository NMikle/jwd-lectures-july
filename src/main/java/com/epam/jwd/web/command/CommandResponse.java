package com.epam.jwd.web.command;

public interface CommandResponse {

    boolean isRedirect();

    String getPath();

}
