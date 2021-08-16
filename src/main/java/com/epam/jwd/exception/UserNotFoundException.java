package com.epam.jwd.exception;

public class UserNotFoundException extends EntityNotFoundException {

    private static final long serialVersionUID = 4063730715215523742L;

    public UserNotFoundException(String message) {
        super(message);
    }
}
