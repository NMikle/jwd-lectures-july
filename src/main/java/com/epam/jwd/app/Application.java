package com.epam.jwd.app;

import com.epam.jwd.model.User;
import com.epam.jwd.model.UserHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        UserHolder users = UserHolder.create();
        for (int i = 0; i < 10; i++) {
            users.save(new User(String.format("Bob%s", i + 1), 21 + i));
        }
        users.remove(4);
        users.remove(new User("Bob7", 27));
        users.save(new User("Bob16", 16), 7);
//        for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
//            User user = iterator.next();
//            System.out.println(user);
//        }
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println(users.size());

//        final int annsIndex = users.save(new User("Ann", 24));
//        System.out.println(users.retrieve(annsIndex));
//        System.out.println(users.retrieve(10));
    }
}
