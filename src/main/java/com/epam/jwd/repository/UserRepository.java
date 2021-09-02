package com.epam.jwd.repository;

import com.epam.jwd.exception.UserNotFoundException;
import com.epam.jwd.model.User;

import java.util.Optional;

public interface UserRepository extends Repository<User> {

    Optional<User> read(int id) throws UserNotFoundException;

    Optional<User> update(User user) throws UserNotFoundException;

}
