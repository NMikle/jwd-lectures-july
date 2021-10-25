package com.epam.jwd.web.command;

import com.epam.jwd.web.controller.RequestFactory;

public enum ShowLoginPageCommand implements Command {
    INSTANCE(RequestFactory.getInstance());

    private static final String LOGIN_JSP_PATH = "/WEB-INF/jsp/login.jsp";

    private final RequestFactory requestFactory;

    ShowLoginPageCommand(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(LOGIN_JSP_PATH);
    }
}
