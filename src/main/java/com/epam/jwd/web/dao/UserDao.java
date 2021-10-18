package com.epam.jwd.web.dao;

import com.epam.jwd.web.model.User;

public interface UserDao extends EntityDao<User> {
    static UserDao instance() {
        return MethodUserDao.getInstance();
    }
}
