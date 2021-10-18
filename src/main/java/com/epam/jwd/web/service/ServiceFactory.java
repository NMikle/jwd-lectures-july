package com.epam.jwd.web.service;

import com.epam.jwd.web.model.Entity;

public interface ServiceFactory {

    <T extends Entity> EntityService<T> serviceFor(Class<T> modelClass);

    static SimpleServiceFactory simple() {
        return SimpleServiceFactory.INSTANCE;
    }

}
