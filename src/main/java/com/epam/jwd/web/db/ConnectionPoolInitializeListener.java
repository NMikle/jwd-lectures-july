package com.epam.jwd.web.db;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ConnectionPoolInitializeListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool.instance().init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.instance().shutDown();
    }
}
