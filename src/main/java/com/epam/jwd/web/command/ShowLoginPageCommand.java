package com.epam.jwd.web.command;

import com.epam.jwd.web.controller.PropertyContext;
import com.epam.jwd.web.controller.RequestFactory;

public enum ShowLoginPageCommand implements Command {
    INSTANCE(RequestFactory.getInstance(), PropertyContext.instance());

    private static final String LOGIN_PAGE = "page.login";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowLoginPageCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(LOGIN_PAGE));
    }
}
