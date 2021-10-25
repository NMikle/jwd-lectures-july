package com.epam.jwd.web.command;

import com.epam.jwd.web.controller.RequestFactory;
import com.epam.jwd.web.model.Bike;
import com.epam.jwd.web.service.EntityService;
import com.epam.jwd.web.service.ServiceFactory;

import java.util.List;

public enum ShowBikesPageCommand implements Command {
    INSTANCE(ServiceFactory.simple().serviceFor(Bike.class),
            RequestFactory.getInstance());

    private static final String BIKES_ATTRIBUTE_NAME = "bikes";
    private static final String BIKES_JSP_PATH = "/WEB-INF/jsp/bikes.jsp";

    private final EntityService<Bike> service;
    private final RequestFactory requestFactory;

    ShowBikesPageCommand(EntityService<Bike> service, RequestFactory requestFactory) {
        this.service = service;
        this.requestFactory = requestFactory;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final List<Bike> bikes = service.findAll();
        request.addAttributeToJsp(BIKES_ATTRIBUTE_NAME, bikes);
        return requestFactory.createForwardResponse(BIKES_JSP_PATH);
    }

}
