package com.epam.jwd.holder;

import com.epam.jwd.model.User;

import java.util.Iterator;

public class DbUserHolder implements UserHolder {
    @Override
    public int save(User user) {
        return 0;
    }

    @Override
    public User save(User user, int index) {
        return null;
    }

    @Override
    public User retrieve(int index) {
        return null;
    }

    @Override
    public int remove(User user) {
        return 0;
    }

    @Override
    public User remove(int index) {
        return null;
    }

    @Override
    public int indexOf(User user) {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public Iterator<User> iterator() {
        return null;
    }
}
