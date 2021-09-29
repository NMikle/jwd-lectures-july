package com.epam.jwd.holder;

import com.epam.jwd.model.OldUser;

public interface UserHolder extends EntityHolder<OldUser> {

    static UserHolder create() {
        return new ArrayUserHolder();
    }

}
