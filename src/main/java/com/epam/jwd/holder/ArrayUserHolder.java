package com.epam.jwd.holder;

import com.epam.jwd.model.OldUser;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayUserHolder implements UserHolder {

    private static final int INITIAL_USER_AMOUNT = 8;
    private static final int GROWTH_FACTOR = 4;

    private OldUser[] oldUsers;
    private int size;

    public ArrayUserHolder() {
        initialize();
    }

    @Override
    public int save(OldUser oldUser) {
        growIfLimitReached();
        oldUsers[size++] = oldUser;
        return size;
    }

    @Override
    public OldUser save(OldUser oldUser, int index) {
        if (!isInBorders(index) || size < --index) {
            return null;
        }
        growIfLimitReached();
        System.arraycopy(oldUsers, index, oldUsers, index + 1, size - index);
        oldUsers[index] = oldUser;
        size++;
        return oldUser;
    }

    @Override
    public OldUser retrieve(int index) {
        return isInBorders(index) ? oldUsers[--index] : null;
    }

    @Override
    public int remove(OldUser oldUser) {
        int index = indexOf(oldUser);
        if (index != -1) {
            this.remove(++index);
        }
        return index;
    }

    @Override
    public OldUser remove(int index) {
        OldUser removed = null;
        if (isInBorders(index)) {
            removed = oldUsers[--index];
            oldUsers[index] = null;
            System.arraycopy(oldUsers, index + 1, oldUsers, index, size - index);
            size--;
        }
        return removed;
    }

    public int indexOf(OldUser oldUser) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            final OldUser savedOldUser = oldUsers[i];
            if (savedOldUser.equals(oldUser)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        initialize();
    }

    @Override
    public Iterator<OldUser> iterator() {
        return new UserIterator();
    }

    private void initialize() {
        this.oldUsers = new OldUser[INITIAL_USER_AMOUNT];
        this.size = 0;
    }

    private void growIfLimitReached() {
        if (size >= oldUsers.length) {
            oldUsers = Arrays.copyOf(oldUsers, size + GROWTH_FACTOR);
        }
    }

    private boolean isInBorders(int index) {
        return index < oldUsers.length + 1 && index > 0;
    }

    private class UserIterator implements Iterator<OldUser> {

        private int pointer;

        public UserIterator() {
            this.pointer = 0;
        }

        @Override
        public boolean hasNext() {
            return size > pointer;
        }

        @Override
        public OldUser next() {
            return oldUsers[pointer++];
        }
    }
}
