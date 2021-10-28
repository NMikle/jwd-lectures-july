package com.epam.jwd.web.command;

import com.epam.jwd.web.controller.PropertyContext;
import com.epam.jwd.web.controller.RequestFactory;

public enum LogoutCommand implements Command {
    INSTANCE(RequestFactory.getInstance(), PropertyContext.instance());

    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";
    private static final String INDEX_PAGE = "page.index";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    LogoutCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        if (noLoggedInUserPresent(request)) {
            //todo: error - no user found cannot logout
            return null;
        }
        request.clearSession();
        return requestFactory.createRedirectResponse(propertyContext.get(INDEX_PAGE));
    }

    private boolean noLoggedInUserPresent(CommandRequest request) {
        return !request.sessionExists() || (
                request.sessionExists()
                        && !request.retrieveFromSession(USER_SESSION_ATTRIBUTE_NAME)
                        .isPresent()
        );
    }
}
