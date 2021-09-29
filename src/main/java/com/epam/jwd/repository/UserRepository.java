package com.epam.jwd.repository;

import com.epam.jwd.exception.UserNotFoundException;
import com.epam.jwd.model.OldUser;

import java.util.Optional;

public interface UserRepository extends Repository<OldUser> {

    Optional<OldUser> read(int id) throws UserNotFoundException;

    Optional<OldUser> update(OldUser oldUser) throws UserNotFoundException;

}
