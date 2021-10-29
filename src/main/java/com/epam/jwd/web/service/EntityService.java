package com.epam.jwd.web.service;

import com.epam.jwd.web.model.Entity;

import java.util.List;
import java.util.Optional;

public interface EntityService<T extends Entity> {

    List<T> findAll();

    Optional<T> create(T entity);

}
