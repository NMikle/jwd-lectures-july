package com.epam.jwd.repository;

import com.epam.jwd.exception.UserNotFoundException;
import com.epam.jwd.model.User;
import com.epam.jwd.holder.UserHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InMemoryUserRepository implements UserRepository {

    private static final Logger LOG = LogManager.getLogger(InMemoryUserRepository.class);
    private static final String USER_NOT_FOUND_BY_ID_MSG = "User with id %s not found";

    private final UserHolder users;
    private int maxId;

    public InMemoryUserRepository(UserHolder users) {
        this.users = users;
        this.maxId = 0;
    }

    @Override
    public User create(User user) {
        int id = ++maxId;
        final User userWithId = user.withId(id);
        users.save(userWithId);
        return userWithId;
    }

    private int findMaxId() {
        return users.size() + 1;
    }

    @Override
    public User read(int id) throws UserNotFoundException {
        final User user = findUserById(id);
        if (user == null) {
            throw new UserNotFoundException(String.format(USER_NOT_FOUND_BY_ID_MSG, id));
        }
        return user;
    }

    @Override
    public User update(User user) throws UserNotFoundException {
        final User savedUser = this.read(user.getId());
        final int userIndex = users.indexOf(savedUser);
        users.save(user, userIndex);
        return user;
    }

    @Override
    public void delete(int id) {
        try {
            final User user = this.read(id);
            users.remove(user);
        } catch (UserNotFoundException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private User findUserById(int id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
}
