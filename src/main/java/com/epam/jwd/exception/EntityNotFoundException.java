package com.epam.jwd.exception;

import com.epam.jwd.model.Entity;

public class EntityNotFoundException extends Exception {

    private static final long serialVersionUID = -7644344358981298559L;

    private final Class<? extends Entity> entityClass;

    public EntityNotFoundException(String message) {
        super(message);
        this.entityClass = null;
    }

    public <T extends Entity> EntityNotFoundException(String message, Class<T> entityClass) {
        super(message);
        this.entityClass = entityClass;
    }

    public Class<? extends Entity> getEntityClass() {
        return entityClass;
    }

//    public static void main(String[] args) throws EntityNotFoundException {
//        try {
//            throw new EntityNotFoundException("message", Employee.class);
//        } catch (EntityNotFoundException e) {
//            final Class<? extends Entity> entityClass = e.getEntityClass();
//        }
//    }
//
//    private static <T> void something(T t) {
//
//    }
}
