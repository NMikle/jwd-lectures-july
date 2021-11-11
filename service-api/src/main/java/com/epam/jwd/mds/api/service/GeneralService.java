package com.epam.jwd.mds.api.service;

import com.epam.jwd.mds.api.exception.CouldNotCreateEntityException;
import com.epam.jwd.mds.api.model.Entity;

import java.util.List;

public interface GeneralService<T extends Entity> {

    T save(T entity) throws CouldNotCreateEntityException;

    List<T> findAll();

}
