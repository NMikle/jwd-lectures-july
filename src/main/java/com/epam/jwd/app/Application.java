package com.epam.jwd.app;

import com.epam.jwd.model.Color;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        System.out.println("Program Start");
        final Color color = Color.of("fF0000");
        System.out.println(color);
    }

    private static void method(Color color) {
        switch (color) {
            case GREEN:
            case RED:
                System.out.println("Not interesting");
                break;
            case BLUE:
                System.out.println("blue");
                break;
            default:
                throw new RuntimeException("not implemented");
        }
    }

}