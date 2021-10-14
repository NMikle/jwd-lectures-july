package com.epam.jwd.web.command;

import com.epam.jwd.web.model.Bike;

import java.util.Arrays;
import java.util.List;

public enum ShowBikesPageCommand implements Command {
    INSTANCE;

    private static final List<Bike> BIKES = Arrays.asList(
            new Bike(1L, "Giant"),
            new Bike(2L, "Aist"),
            new Bike(3L, "Stels")
    );
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

    @Override
    public CommandResponse execute(CommandRequest request) {
        //todo: get bikes from database
        request.addAttributeToJsp(BIKES_ATTRIBUTE_NAME, BIKES);
        return FORWARD_TO_BIKES_PAGE;
    }
}
