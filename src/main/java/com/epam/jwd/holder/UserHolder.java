package com.epam.jwd.holder;

import com.epam.jwd.model.User;

public interface UserHolder extends EntityHolder<User> {

    static UserHolder create() {
        return new ArrayUserHolder();
    }

}
