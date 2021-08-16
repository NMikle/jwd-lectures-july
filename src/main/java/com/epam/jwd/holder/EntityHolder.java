package com.epam.jwd.holder;

import com.epam.jwd.model.Entity;

public interface EntityHolder<T extends Entity<T>> extends Iterable<T> {

    int save(T entity);

    T save(T entity, int index);

    T retrieve(int index);

    int remove(T entity);

    T remove(int index);

    int indexOf(T entity);

    int size();

    void clear();

}
