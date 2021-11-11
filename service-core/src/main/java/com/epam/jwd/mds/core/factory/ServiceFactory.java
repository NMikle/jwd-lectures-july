package com.epam.jwd.mds.core.factory;

import com.epam.jwd.mds.api.service.EmployeeService;
import com.epam.jwd.mds.core.service.ListEmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class ServiceFactory {

    private static final Logger LOGGER = LogManager.getLogger(ServiceFactory.class);

    public static EmployeeService provider() {
        LOGGER.info("provider method called");
        return new ListEmployeeService(new ArrayList<>());
    }

}
