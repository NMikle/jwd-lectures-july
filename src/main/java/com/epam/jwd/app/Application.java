package com.epam.jwd.app;

import com.epam.jwd.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        Object human = new User("Paul", 12);
        Object anotherHuman = new User("Paul", 12);
        Object thirdHuman = new User("Paul", 12);

        System.out.println(human.equals(human)); //ref

        System.out.println(anotherHuman.equals(human));//sym
        System.out.println(human.equals(anotherHuman));//sym

        System.out.println(human.equals(anotherHuman));
        System.out.println(anotherHuman.equals(thirdHuman));
        System.out.println(human.equals(thirdHuman)); //trans

        System.out.println(human.equals(null)); //no NPE

        System.out.println(human.hashCode());
        System.out.println(human);
    }
}