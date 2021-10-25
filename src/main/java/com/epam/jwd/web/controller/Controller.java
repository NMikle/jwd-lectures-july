package com.epam.jwd.web.controller;

import com.epam.jwd.web.command.Command;
import com.epam.jwd.web.command.CommandRequest;
import com.epam.jwd.web.command.CommandResponse;
import com.epam.jwd.web.db.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 7792798972556410954L;

    private static final Logger LOG = LogManager.getLogger(Controller.class);

    private static final String COMMAND_NAME_PARAM = "command";

    private final RequestFactory requestFactory = RequestFactory.getInstance();

    @Override
    public void init() {
        ConnectionPool.instance().init();
    }

    @Override
    public void destroy() {
        ConnectionPool.instance().shutDown();
    }

    @Override
    protected void doGet(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        LOG.trace("caught req and resp in doGet method");
        processRequest(httpRequest, httpResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        LOG.trace("caught req and resp in doPost method");
        processRequest(httpRequest, httpResponse);
    }

    private void processRequest(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        final String commandName = httpRequest.getParameter(COMMAND_NAME_PARAM);
        final Command command = Command.of(commandName);
        final CommandRequest commandRequest = requestFactory.createRequest(httpRequest);
        final CommandResponse commandResponse = command.execute(commandRequest);
        proceedWithResponse(httpRequest, httpResponse, commandResponse);
    }

    private void proceedWithResponse(HttpServletRequest req, HttpServletResponse resp,
                                     CommandResponse commandResponse) {
        try {
            forwardOrRedirectToResponseLocation(req, resp, commandResponse);
        } catch (ServletException e) {
            LOG.error("servlet exception occurred", e);
        } catch (IOException e) {
            LOG.error("IO exception occurred", e);
        }
    }

    private void forwardOrRedirectToResponseLocation(HttpServletRequest req, HttpServletResponse resp,
                                                     CommandResponse commandResponse) throws IOException, ServletException {
        if (commandResponse.isRedirect()) {
            resp.sendRedirect(commandResponse.getPath());
        } else {
            final String desiredPath = commandResponse.getPath();
            final RequestDispatcher dispatcher = req.getRequestDispatcher(desiredPath);
            dispatcher.forward(req, resp);
        }
    }
}
