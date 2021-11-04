package com.epam.jwd.web.service;

import com.epam.jwd.web.dao.BikeDao;
import com.epam.jwd.web.dao.UserDao;
import com.epam.jwd.web.db.TransactionManager;
import com.epam.jwd.web.model.Bike;
import com.epam.jwd.web.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BikeService implements EntityService<Bike> {

    private static final Logger LOGGER = LogManager.getLogger(BikeService.class);

    private final BikeDao bikeDao;
    private final UserDao userDao;

    BikeService(BikeDao bikeDao, UserDao userDao) {
        this.bikeDao = bikeDao;
        this.userDao = userDao;
    }


    @Override
    @Transactional
    public List<Bike> findAll() {
        LOGGER.trace("retrieving bikes");
        return bikeDao.read()
                .stream()
                .map(bike -> {
                    final Long bikeOwnerId = bikeDao.findUserIdByBikeId(bike.getId()).orElse(null);
                    final Optional<User> bikeOwner = userDao.read(bikeOwnerId);
                    return bike.withOwner(bikeOwner.orElse(null));
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Bike> create(Bike entity) {
        return Optional.empty(); //todo: implement
    }
}
