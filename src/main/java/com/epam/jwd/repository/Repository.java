package com.epam.jwd.repository;

import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.model.Entity;

import java.util.Optional;

public interface Repository<T extends Entity<T>> {

    Optional<T> create(T entity);

    Optional<T> read(int id) throws EntityNotFoundException;

    Optional<T> update(T entity) throws EntityNotFoundException;

    void delete(int id);

}
