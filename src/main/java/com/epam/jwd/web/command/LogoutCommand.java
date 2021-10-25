package com.epam.jwd.web.command;

import com.epam.jwd.web.controller.RequestFactory;

public enum LogoutCommand implements Command {
    INSTANCE(RequestFactory.getInstance());

    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";
    private static final String INDEX_PATH = "/";

    private final RequestFactory requestFactory;

    LogoutCommand(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        if (noLoggedInUserPresent(request)) {
            //todo: error - no user found cannot logout
            return null;
        }
        request.clearSession();
        return requestFactory.createRedirectResponse(INDEX_PATH);
    }

    private boolean noLoggedInUserPresent(CommandRequest request) {
        return !request.sessionExists() || (
                request.sessionExists()
                        && !request.retrieveFromSession(USER_SESSION_ATTRIBUTE_NAME)
                        .isPresent()
        );
    }
}
