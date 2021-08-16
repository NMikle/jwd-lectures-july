package com.epam.jwd.model;

public interface Entity<T extends Entity<T>> {

    Integer getId();

    T withId(Integer id);

}
