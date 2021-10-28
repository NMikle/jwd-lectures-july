package com.epam.jwd.web.command;

import com.epam.jwd.web.controller.PropertyContext;
import com.epam.jwd.web.controller.RequestFactory;

public enum ShowMainPageCommand implements Command {
    INSTANCE(RequestFactory.getInstance(), PropertyContext.instance());

    private static final String MAIN_PAGE = "page.main";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowMainPageCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(MAIN_PAGE));
    }

}
