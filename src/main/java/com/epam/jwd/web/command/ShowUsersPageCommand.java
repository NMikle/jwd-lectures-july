package com.epam.jwd.web.command;

import com.epam.jwd.web.controller.PropertyContext;
import com.epam.jwd.web.controller.RequestFactory;
import com.epam.jwd.web.model.User;
import com.epam.jwd.web.service.ServiceFactory;
import com.epam.jwd.web.service.UserService;

import java.util.List;

public enum ShowUsersPageCommand implements Command {
    INSTANCE(ServiceFactory.simple().userService(),
            RequestFactory.getInstance(),
            PropertyContext.instance());

    private static final String USERS_PAGE = "page.users";
    private static final String JSP_USERS_ATTRIBUTE_NAME = "users";

    private final UserService userService;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowUsersPageCommand(UserService userService, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.userService = userService;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final List<User> users = userService.findAll();
        request.addAttributeToJsp(JSP_USERS_ATTRIBUTE_NAME, users);
        return requestFactory.createForwardResponse(propertyContext.get(USERS_PAGE));
    }
}
