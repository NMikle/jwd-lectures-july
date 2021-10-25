package com.epam.jwd.web.controller;

import com.epam.jwd.web.command.CommandRequest;
import com.epam.jwd.web.command.CommandResponse;

import javax.servlet.http.HttpServletRequest;

public interface RequestFactory {

    CommandRequest createRequest(HttpServletRequest request);

    CommandResponse createForwardResponse(String path);

    CommandResponse createRedirectResponse(String path);

    static RequestFactory getInstance() {
        return SimpleRequestFactory.INSTANCE;
    }

}
