package com.revature.quizzard.exceptions;

public class ResourcePersistenceException extends RuntimeException {

    public ResourcePersistenceException() {
        super("An unspecified error occurred while attempting to persist the provided resource!");
    }

    public ResourcePersistenceException(Exception e) {
        super("An error occurred while attempting to persist the provided resource!", e);
    }

    public ResourcePersistenceException(String message) {
        super(message);
    }

}
