package com.epam.jwd.concurrency;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StaleValue implements Runnable {

    private static final Logger LOG = LogManager.getLogger(StaleValue.class);

    private boolean sleep;

    public void setSleep(boolean sleep) {
        this.sleep = sleep;
    }

    @Override
    public void run() {
        int counter = 0;
        while (!sleep) {
            counter++;
        }
        LOG.info("finished counting. counter: {}", counter);
    }
}
