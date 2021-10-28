package com.epam.jwd.web.command;

import com.epam.jwd.web.controller.PropertyContext;
import com.epam.jwd.web.controller.RequestFactory;

public enum ShowErrorPageCommand implements Command {
    INSTANCE(RequestFactory.getInstance(),
            PropertyContext.instance());

    private static final String ERROR_PAGE = "page.error";

    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowErrorPageCommand(RequestFactory requestFactory, PropertyContext propertyContext) {
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return requestFactory.createForwardResponse(propertyContext.get(ERROR_PAGE));

    }
}
