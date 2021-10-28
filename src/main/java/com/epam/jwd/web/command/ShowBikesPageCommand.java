package com.epam.jwd.web.command;

import com.epam.jwd.web.controller.PropertyContext;
import com.epam.jwd.web.controller.RequestFactory;
import com.epam.jwd.web.model.Bike;
import com.epam.jwd.web.service.EntityService;
import com.epam.jwd.web.service.ServiceFactory;

import java.util.List;

public enum ShowBikesPageCommand implements Command {
    INSTANCE(ServiceFactory.simple().serviceFor(Bike.class),
            RequestFactory.getInstance(), PropertyContext.instance());

    private static final String BIKES_ATTRIBUTE_NAME = "bikes";
    private static final String BIKES_PAGE = "page.bikes";

    private final EntityService<Bike> service;
    private final RequestFactory requestFactory;
    private final PropertyContext propertyContext;

    ShowBikesPageCommand(EntityService<Bike> service, RequestFactory requestFactory, PropertyContext propertyContext) {
        this.service = service;
        this.requestFactory = requestFactory;
        this.propertyContext = propertyContext;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final List<Bike> bikes = service.findAll();
        request.addAttributeToJsp(BIKES_ATTRIBUTE_NAME, bikes);
        return requestFactory.createForwardResponse(propertyContext.get(BIKES_PAGE));
    }

}
