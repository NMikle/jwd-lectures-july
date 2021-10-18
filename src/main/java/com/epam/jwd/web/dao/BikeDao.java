package com.epam.jwd.web.dao;

import com.epam.jwd.web.model.Bike;

import java.util.List;
import java.util.Optional;

public interface BikeDao extends EntityDao<Bike> {

    List<Bike> findByModel(String model);

    Optional<Long> findUserIdByBikeId(Long id);

    static BikeDao instance() {
        return MethodBikeDao.getInstance();
    }

}
