package com.epam.jwd.app;

import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.holder.ArrayEntityHolder;
import com.epam.jwd.holder.EntityHolder;
import com.epam.jwd.model.User;
import com.epam.jwd.repository.InMemoryEntityRepository;
import com.epam.jwd.repository.Repository;
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
        Repository<User> repo = new InMemoryEntityRepository<>(userHolder);
        repo.create(User.createUser("Bob", 22));
        repo.create(User.createUser("Alice", 31));
        repo.create(User.createUser("Paul", 44));
        for (int i = 1; i < 4; i++) {
            try {
                System.out.println(repo.read(i));
            } catch (EntityNotFoundException e) {
                LOG.error("error while reading by id", e);
            }
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
