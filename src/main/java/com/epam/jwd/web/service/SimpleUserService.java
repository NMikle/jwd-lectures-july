package com.epam.jwd.web.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.epam.jwd.web.dao.UserDao;
import com.epam.jwd.web.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static at.favre.lib.crypto.bcrypt.BCrypt.MIN_COST;

public class SimpleUserService implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(SimpleUserService.class);

    private static final byte[] DUMMY_PASSWORD = "password".getBytes(StandardCharsets.UTF_8);
    private final UserDao dao;
    private final BCrypt.Hasher hasher;
    private final BCrypt.Verifyer verifier;

    public SimpleUserService(UserDao dao, BCrypt.Hasher hasher,
                             BCrypt.Verifyer verifier) {
        this.dao = dao;
        this.hasher = hasher;
        this.verifier = verifier;
    }

    @Override
    public List<User> findAll() {
        LOGGER.trace("retrieving all users");
        return dao.read();
    }

    @Override
    public Optional<User> create(User user) {
        final char[] rawPassword = user.getPassword().toCharArray();
        final String hashedPassword = hasher.hashToString(MIN_COST, rawPassword);
        return Optional.ofNullable(dao.create(user.withPassword(hashedPassword)));
    }

    @Override
    @Transactional
    public Optional<User> authenticate(String email, String password) {
        LOGGER.trace("authenticating user");
        if (email == null || password == null) {
            return Optional.empty();
        }
        final byte[] enteredPassword = password.getBytes(StandardCharsets.UTF_8);
        final Optional<User> readUser = dao.readUserByEmail(email);
        if (readUser.isPresent()) {
            final byte[] hashedPassword = readUser.get()
                    .getPassword()
                    .getBytes(StandardCharsets.UTF_8);
            return verifier.verify(enteredPassword, hashedPassword).verified
                    ? readUser
                    : Optional.empty();
        } else {
            protectFromTimingAttack(enteredPassword);
            return Optional.empty();
        }
    }

    private void protectFromTimingAttack(byte[] enteredPassword) {
        verifier.verify(enteredPassword, DUMMY_PASSWORD);
    }
}
