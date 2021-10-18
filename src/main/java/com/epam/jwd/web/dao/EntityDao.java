package com.epam.jwd.web.dao;

import com.epam.jwd.web.model.Entity;

import java.util.List;
import java.util.Optional;

public interface EntityDao<T extends Entity> {

    T create(T entity);

    List<T> read();

    Optional<T> read(Long id);

    T update(T entity);

    boolean delete(Long id);

}
