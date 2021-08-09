package com.epam.jwd.holder;

import com.epam.jwd.model.User;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayUserHolder implements UserHolder {

    private static final int INITIAL_USER_AMOUNT = 8;
    private static final int GROWTH_FACTOR = 4;

    private User[] users;
    private int size;

    public ArrayUserHolder() {
        initialize();
    }

    @Override
    public int save(User user) {
        growIfLimitReached();
        users[size++] = user;
        return size;
    }

    @Override
    public User save(User user, int index) {
        if (!isInBorders(index) || size < --index) {
            return null;
        }
        growIfLimitReached();
        System.arraycopy(users, index, users, index + 1, size - index);
        users[index] = user;
        size++;
        return user;
    }

    @Override
    public User retrieve(int index) {
        return isInBorders(index) ? users[--index] : null;
    }

    @Override
    public int remove(User user) {
        int index = indexOf(user);
        if (index != -1) {
            this.remove(++index);
        }
        return index;
    }

    @Override
    public User remove(int index) {
        User removed = null;
        if (isInBorders(index)) {
            removed = users[--index];
            users[index] = null;
            System.arraycopy(users, index + 1, users, index, size - index);
            size--;
        }
        return removed;
    }

    public int indexOf(User user) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            final User savedUser = users[i];
            if (savedUser.equals(user)) {
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
    public Iterator<User> iterator() {
        return new UserIterator();
    }

    private void initialize() {
        this.users = new User[INITIAL_USER_AMOUNT];
        this.size = 0;
    }

    private void growIfLimitReached() {
        if (size >= users.length) {
            users = Arrays.copyOf(users, size + GROWTH_FACTOR);
        }
    }

    private boolean isInBorders(int index) {
        return index < users.length + 1 && index > 0;
    }

    private class UserIterator implements Iterator<User> {

        private int pointer;

        public UserIterator() {
            this.pointer = 0;
        }

        @Override
        public boolean hasNext() {
            return size > pointer;
        }

        @Override
        public User next() {
            return users[pointer++];
        }
    }
}
