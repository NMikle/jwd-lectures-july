package com.epam.jwd.repository;

import com.epam.jwd.exception.UserNotFoundException;
import com.epam.jwd.model.User;

public interface UserRepository {

    User create(User user);

    User read(int id) throws UserNotFoundException;

    User update(User user) throws UserNotFoundException;

    void delete(int id);

}
