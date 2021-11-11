package com.epam.jwd.mds.api.exception;

import com.epam.jwd.mds.api.model.Entity;

import java.io.Serial;

public class CouldNotCreateEntityException extends Exception {

    @Serial
    private static final long serialVersionUID = 2261314926443119574L;

    private final Class<?> entity;
    private final Object param;

    public <T extends Entity> CouldNotCreateEntityException(String message, Class<T> entity, Object param) {
        super(message);
        this.entity = entity;
        this.param = param;
    }

    @SuppressWarnings("unchecked")
    public Class<? extends Entity> getEntity() {
        return (Class<? extends Entity>) entity;
    }

    public Object getParam() {
        return param;
    }
}
