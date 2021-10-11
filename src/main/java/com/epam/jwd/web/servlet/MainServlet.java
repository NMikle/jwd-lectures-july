package com.epam.jwd.web.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cool-servlet")
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 2281486690274283919L;

    private static final Logger LOG = LogManager.getLogger(MainServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        LOG.trace("caught req and resp in doGet method");
        try (final PrintWriter writer = resp.getWriter()) {
            writer.write("<html><head></head>" +
                    "<body><h1>Hello World</h1><p>sent by cool-servlet</p></body>" +
                    "</html>");
        } catch (IOException e) {
            LOG.error("IO exception occurred", e);
        }
    }
}
