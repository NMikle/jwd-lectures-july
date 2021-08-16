package com.epam.jwd.repository;

import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.model.Entity;

public interface Repository<T extends Entity<T>> {

    T create(T entity);

    T read(int id) throws EntityNotFoundException;

    T update(T entity) throws EntityNotFoundException;

    void delete(int id);

}
