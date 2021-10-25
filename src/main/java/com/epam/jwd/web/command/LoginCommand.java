package com.epam.jwd.web.command;

import com.epam.jwd.web.controller.RequestFactory;
import com.epam.jwd.web.model.User;
import com.epam.jwd.web.service.ServiceFactory;
import com.epam.jwd.web.service.UserService;

import java.util.Optional;

public enum LoginCommand implements Command {
    INSTANCE(ServiceFactory.simple().userService(), RequestFactory.getInstance());

    private static final String INDEX_PATH = "/";
    private static final String LOGIN_JSP_PATH = "/WEB-INF/jsp/login.jsp";

    private static final String ERROR_LOGIN_PASS_ATTRIBUTE = "errorLoginPassMessage";
    private static final String USER_SESSION_ATTRIBUTE_NAME = "user";
    private static final String LOGIN_REQUEST_PARAM_NAME = "login";
    private static final String PASSWORD_REQUEST_PARAM_NAME = "password";
    private static final String ERROR_LOGIN_PASS_MESSAGE = "Invalid login or password";

    private final UserService userService;
    private final RequestFactory requestFactory;

    LoginCommand(UserService userService, RequestFactory requestFactory) {
        this.userService = userService;
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        if (request.sessionExists() && request.retrieveFromSession(USER_SESSION_ATTRIBUTE_NAME).isPresent()) {
            //todo: error - user already logged in
            return null;
        }
        final String login = request.getParameter(LOGIN_REQUEST_PARAM_NAME);
        final String password = request.getParameter(PASSWORD_REQUEST_PARAM_NAME);
        final Optional<User> user = userService.authenticate(login, password);
        if (!user.isPresent()) {
            request.addAttributeToJsp(ERROR_LOGIN_PASS_ATTRIBUTE, ERROR_LOGIN_PASS_MESSAGE);
            return requestFactory.createForwardResponse(LOGIN_JSP_PATH);
        }
        request.clearSession();
        request.createSession();
        request.addToSession(USER_SESSION_ATTRIBUTE_NAME, user.get());
        return requestFactory.createRedirectResponse(INDEX_PATH);
    }
}
