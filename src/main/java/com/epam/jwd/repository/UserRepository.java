package com.epam.jwd.repository;

import com.epam.jwd.exception.UserNotFoundException;
import com.epam.jwd.model.User;

public interface UserRepository extends Repository<User> {

    User read(int id) throws UserNotFoundException;

    User update(User user) throws UserNotFoundException;

}
