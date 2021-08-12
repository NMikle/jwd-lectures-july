package com.epam.jwd.app;

import com.epam.jwd.holder.ArrayEntityHolder;
import com.epam.jwd.holder.EntityHolder;
import com.epam.jwd.model.Dog;
import com.epam.jwd.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.function.Function;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        Function<Integer, User[]> userArrayCreationFunction = new Function<Integer, User[]>() {
            @Override
            public User[] apply(Integer integer) {
                return new User[integer];
            }
        };
        EntityHolder<User> userHolder = new ArrayEntityHolder<>(userArrayCreationFunction);
        userHolder.save(new User(1, "A", 5));
        userHolder.save(new User(2, "B", 6));
        userHolder.save(new User(3, "C", 7));
        for (User user : userHolder) {
            System.out.println(user);
        }
    }

    private static void test(EntityHolder<User> holder) {
        final Iterator<User> iterator = holder.iterator();
        final User next = iterator.next();
    }

//    private static final class ArrayCreationFunc implements Function<Integer, User[]> {
//        @Override
//        public User[] apply(Integer integer) {
//            return new User[integer];
//        }
//    }
}
