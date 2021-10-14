package com.epam.jwd.web.dao;

import com.epam.jwd.web.model.Bike;

import java.util.List;

public interface BikeDao extends GenericDao<Bike> {

    List<Bike> findByModel(String model);

}
