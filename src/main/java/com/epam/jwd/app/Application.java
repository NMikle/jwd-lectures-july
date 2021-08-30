package com.epam.jwd.app;

import com.epam.jwd.model.ExceptionFromStaticField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
//        final ExceptionFromStaticField instance = ExceptionFromStaticField.instance;
        final Pattern pattern = Pattern.compile("[a-z]+([1-9])");
        final Matcher matcher = pattern.matcher("input34");
        System.out.println(matcher.groupCount());
        System.out.println(matcher.lookingAt());
//        System.out.println(Pattern.matches("[a-z]+", "input34"));
    }

}
