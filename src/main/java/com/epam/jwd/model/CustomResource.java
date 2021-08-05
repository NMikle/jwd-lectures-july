package com.epam.jwd.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomResource implements AutoCloseable {

    private static final Logger LOG = LogManager.getLogger(CustomResource.class);
    //some code here

    @Override
    public void close() throws Exception {
        LOG.info("closing some resources");
        throw new Exception("something unexpected happened");
    }
}
