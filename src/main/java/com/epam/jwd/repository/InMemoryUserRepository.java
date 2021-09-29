package com.epam.jwd.repository;

import com.epam.jwd.exception.UserNotFoundException;
import com.epam.jwd.holder.UserHolder;
import com.epam.jwd.model.OldUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

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
    public Optional<OldUser> create(OldUser oldUser) {
        int id = ++maxId;
        final OldUser oldUserWithId = oldUser.withId(id);
        users.save(oldUserWithId);
        return Optional.of(oldUserWithId);
    }

    private int findMaxId() {
        return users.size() + 1;
    }

    @Override
    public Optional<OldUser> read(int id) throws UserNotFoundException {
        final OldUser oldUser = findUserById(id);
        if (oldUser == null) {
            throw new UserNotFoundException(String.format(USER_NOT_FOUND_BY_ID_MSG, id));
        }
        return Optional.of(oldUser);
    }

    @Override
    public Optional<OldUser> update(OldUser oldUser) throws UserNotFoundException {
        final OldUser savedOldUser = this.read(oldUser.getId()).get();
        final int userIndex = users.indexOf(savedOldUser);
        users.save(oldUser, userIndex);
        return Optional.of(oldUser);
    }

    @Override
    public void delete(int id) {
        try {
            final OldUser oldUser = this.read(id).get();
            users.remove(oldUser);
        } catch (UserNotFoundException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private OldUser findUserById(int id) {
        for (OldUser oldUser : users) {
            if (oldUser.getId().equals(id)) {
                return oldUser;
            }
        }
        return null;
    }
}
