package com.revature.quizzard.exceptions;

public class ResourcePersistenceException extends RuntimeException {

    public ResourcePersistenceException() {
        super("An error occurred while attempting to persist the provided resource!");
    }

    public ResourcePersistenceException(String message) {
        super(message);
    }

}
