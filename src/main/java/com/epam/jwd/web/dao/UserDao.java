package com.epam.jwd.web.dao;

import com.epam.jwd.web.model.User;

import java.util.Optional;

public interface UserDao extends EntityDao<User> {

    Optional<User> readUserByEmail(String email);

    static UserDao instance() {
        return MethodUserDao.getInstance();
    }
}
