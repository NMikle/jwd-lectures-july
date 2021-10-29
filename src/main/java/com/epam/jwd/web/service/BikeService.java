package com.epam.jwd.web.service;

import com.epam.jwd.web.dao.BikeDao;
import com.epam.jwd.web.dao.UserDao;
import com.epam.jwd.web.db.TransactionManager;
import com.epam.jwd.web.model.Bike;
import com.epam.jwd.web.model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BikeService implements EntityService<Bike> {

    private final BikeDao bikeDao;
    private final UserDao userDao;
    private final TransactionManager transactionManager;

    BikeService(BikeDao bikeDao, UserDao userDao,
                TransactionManager transactionManager) {
        this.bikeDao = bikeDao;
        this.userDao = userDao;
        this.transactionManager = transactionManager;
    }


    @Override
    public List<Bike> findAll() {
        transactionManager.initTransaction();
        final List<Bike> bikes = bikeDao.read()
                .stream()
                .map(bike -> {
                    final Long bikeOwnerId = bikeDao.findUserIdByBikeId(bike.getId()).orElse(null);
                    final Optional<User> bikeOwner = userDao.read(bikeOwnerId);
                    return bike.withOwner(bikeOwner.orElse(null));
                })
                .collect(Collectors.toList());
        transactionManager.commitTransaction();
        return bikes;
    }

    @Override
    public Optional<Bike> create(Bike entity) {
        return Optional.empty(); //todo: implement
    }
}
