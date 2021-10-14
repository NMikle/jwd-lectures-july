package com.epam.jwd.web.dao;

import com.epam.jwd.web.model.Bike;

import java.util.List;
import java.util.Optional;

public class MethodBikeDao implements BikeDao {
    @Override
    public List<Bike> findByModel(String model) {
        return null;
    }

    @Override
    public Bike create(Bike entity) {
        return null;
    }

    @Override
    public List<Bike> read() {
        return null;
    }

    @Override
    public Optional<Bike> read(Long id) {
        return Optional.empty();
    }

    @Override
    public Bike update(Bike entity) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
