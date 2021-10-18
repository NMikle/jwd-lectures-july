package com.epam.jwd.web.service;

import com.epam.jwd.web.model.Entity;

import java.util.List;

public interface EntityService<T extends Entity> {

    List<T> findAll();

}
