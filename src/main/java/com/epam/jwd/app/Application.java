package com.epam.jwd.app;

import com.epam.jwd.model.InheritanceBadPracticeChild;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        final InheritanceBadPracticeChild badPracticeChild = new InheritanceBadPracticeChild("hello world");
        badPracticeChild.testPublicMethod();
    }

}
