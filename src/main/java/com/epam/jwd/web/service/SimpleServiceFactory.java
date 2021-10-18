package com.epam.jwd.web.service;

import com.epam.jwd.web.dao.BikeDao;
import com.epam.jwd.web.dao.UserDao;
import com.epam.jwd.web.db.TransactionManager;
import com.epam.jwd.web.model.Entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public enum SimpleServiceFactory implements ServiceFactory {
    INSTANCE;

    private static final String SERVICE_NOT_FOUND = "Could not create service for %s class";

    private final Map<Class<?>, EntityService<?>> serviceByEntity = new ConcurrentHashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Entity> EntityService<T> serviceFor(Class<T> modelClass) {
        return (EntityService<T>) serviceByEntity
                .computeIfAbsent(modelClass, createServiceFromClass());
    }

    private Function<Class<?>, EntityService<?>> createServiceFromClass() {
        return clazz -> {
            final String className = clazz.getSimpleName();
            switch (className) {
                case "Bike":
                    return new BikeService(BikeDao.instance(), UserDao.instance(), TransactionManager.instance());
                default:
                    throw new IllegalArgumentException(String.format(SERVICE_NOT_FOUND, className));
            }
        };
    }
}
