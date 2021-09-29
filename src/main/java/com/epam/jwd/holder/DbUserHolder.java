package com.epam.jwd.holder;

import com.epam.jwd.model.OldUser;

import java.util.Iterator;

public class DbUserHolder implements UserHolder {
    @Override
    public int save(OldUser oldUser) {
        return 0;
    }

    @Override
    public OldUser save(OldUser oldUser, int index) {
        return null;
    }

    @Override
    public OldUser retrieve(int index) {
        return null;
    }

    @Override
    public int remove(OldUser oldUser) {
        return 0;
    }

    @Override
    public OldUser remove(int index) {
        return null;
    }

    @Override
    public int indexOf(OldUser oldUser) {
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
    public Iterator<OldUser> iterator() {
        return null;
    }
}
