package com.epam.jwd.web.service;

import com.epam.jwd.web.dao.UserDao;
import com.epam.jwd.web.model.User;

import java.util.List;
import java.util.Optional;

public class SimpleUserService implements UserService {

    private final UserDao dao;

    public SimpleUserService(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public List<User> findAll() {
        return dao.read();
    }

    @Override
    public Optional<User> authenticate(String email, String password) {
        final Optional<User> readUser = dao.readUserByEmail(email);
        return readUser.filter(user -> user.getPassword().equals(password)); //todo: hash password
    }
}
