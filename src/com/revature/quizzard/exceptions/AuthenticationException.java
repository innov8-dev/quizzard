package com.revature.quizzard.exceptions;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException() {
        super("Could not authenticate using provided credentials.");
    }

    public AuthenticationException(Throwable cause) {
        super("An unexpected error occurred when attempting to authenticate.", cause);
    }
}
