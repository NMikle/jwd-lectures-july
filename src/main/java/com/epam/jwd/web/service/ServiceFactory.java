package com.epam.jwd.web.service;

import com.epam.jwd.web.model.Entity;
import com.epam.jwd.web.model.User;

public interface ServiceFactory {

    <T extends Entity> EntityService<T> serviceFor(Class<T> modelClass);

    default UserService userService() {
        return (UserService) serviceFor(User.class);
    }

    static SimpleServiceFactory simple() {
        return SimpleServiceFactory.INSTANCE;
    }

}
