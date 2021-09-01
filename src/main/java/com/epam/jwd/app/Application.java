package com.epam.jwd.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
//        final ExceptionFromStaticField instance = ExceptionFromStaticField.instance;
        final String input = "Hello, my phone number is: +375(33)355-27-33. Please call me in case of any questions.";
        final String anotherInput = "Phone number is: +3752955428157.";
        final Pattern pattern = Pattern.compile("\\+375\\s?((\\(25\\)|(25))|(\\(29\\)|(29))|(\\(33\\)|(33))|(\\(44\\)|(44)))\\s?\\d{3}-?\\d{2}-?\\d{2}(?=\\D)");
        final Matcher matcher = pattern.matcher(input);
        System.out.println(matcher.groupCount());
        System.out.println(matcher.find());
        System.out.println(matcher.start());
        System.out.println(matcher.end());
        System.out.println(matcher.group());
        System.out.println(matcher.group(1));

        matcher.reset(anotherInput);
        if (matcher.find()) {
            System.out.println(matcher.group());
            System.out.println(matcher.group(1));
        }
    }

}
