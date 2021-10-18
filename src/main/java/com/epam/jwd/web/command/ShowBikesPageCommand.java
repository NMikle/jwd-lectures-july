package com.epam.jwd.web.command;

import com.epam.jwd.web.model.Bike;
import com.epam.jwd.web.service.EntityService;
import com.epam.jwd.web.service.ServiceFactory;

import java.util.List;

public enum ShowBikesPageCommand implements Command {
    INSTANCE(ServiceFactory.simple().serviceFor(Bike.class));

    private static final String BIKES_ATTRIBUTE_NAME = "bikes";

    private static final CommandResponse FORWARD_TO_BIKES_PAGE = new CommandResponse() {
        @Override
        public boolean isRedirect() {
            return false;
        }

        @Override
        public String getPath() {
            return "/WEB-INF/jsp/bikes.jsp";
        }
    };

    private final EntityService<Bike> service;

    ShowBikesPageCommand(EntityService<Bike> service) {
        this.service = service;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final List<Bike> bikes = service.findAll();
        request.addAttributeToJsp(BIKES_ATTRIBUTE_NAME, bikes);
        return FORWARD_TO_BIKES_PAGE;
    }
}
