package com.revature.quizzard.exceptions;

public class DataSourceException extends RuntimeException {
    public DataSourceException() {
        super("An unspecified error occurred while reading from the data source.");
    }

    public DataSourceException(Throwable cause) {
        super("An error occurred while reading from the data source.", cause);
    }
}
