package com.revature.quizzard.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("No resources found with provided search criteria.");
    }

    public ResourceNotFoundException(Exception e) {
        super("No resources found with provided search criteria.", e);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
