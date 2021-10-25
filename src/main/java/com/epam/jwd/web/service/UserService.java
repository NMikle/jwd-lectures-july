package com.epam.jwd.web.service;

import com.epam.jwd.web.model.User;

import java.util.Optional;

public interface UserService extends EntityService<User> {

    Optional<User> authenticate(String email, String password);

}
