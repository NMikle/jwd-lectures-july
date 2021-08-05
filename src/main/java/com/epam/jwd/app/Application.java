package com.epam.jwd.app;

import com.epam.jwd.model.CustomResource;
import com.epam.jwd.model.User;
import com.epam.jwd.model.UserHolder;
import com.epam.jwd.repository.InMemoryUserRepository;
import com.epam.jwd.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        UserRepository repo = new InMemoryUserRepository(UserHolder.create());
        repo.create(User.createUser("Bob", 34));
        repo.create(User.createUser("Alice", 33));
        repo.create(User.createUser("Lynn", 26));
//        for (int i = 0; i < 5; i++) {
//            try {
//                LOG.info("User with id {} found: {}", i, repo.read(i));
//            } catch (UserNotFoundException e) {
//                LOG.error(e.getMessage(), e);
//            } finally {
//                LOG.info("Hey from finally");
//            }
//        }
        LOG.info(tryWithResourcesTestMethod());
    }

    private static int tryWithResourcesTestMethod() {
        try (CustomResource resource = new CustomResource()) {
            LOG.trace("try start");
            LOG.trace("try end");
            ((String) null).length();
            throw new ArithmeticException("some bad arithmetic");
//            return 88;
        } catch (ArithmeticException | NullPointerException e) {
            LOG.trace("arithmetic catch");
            LOG.error(e.getMessage(), e);
            return 11;
        } catch (Exception e) {
            LOG.trace("general catch");
            LOG.error(e.getMessage(), e);
            return 50;
        } finally {
            LOG.trace("finally");
        }
    }

    private static int finallyTestMethod(UserRepository repo) {
        try {
            LOG.trace("try start");
            repo.read(-1);
            LOG.trace("try end");
            return 80;
        } catch (Exception e) {
            LOG.trace("catch");
            return 20;
        } finally {
            LOG.trace("finally");
            return 21; // bad practice
        }
    }
}
