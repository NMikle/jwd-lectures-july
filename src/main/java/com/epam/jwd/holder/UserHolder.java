package com.epam.jwd.holder;

import com.epam.jwd.model.User;

public interface UserHolder extends Iterable<User> {

    int save(User user);

    User save(User user, int index);

    User retrieve(int index);

    int remove(User user);

    User remove(int index);

    int indexOf(User user);

    int size();

    void clear();

    static UserHolder create() {
        return new ArrayUserHolder();
    }

}
