package com.eism.exception;

public class EismException extends RuntimeException {
    // Creates a project error.
    public EismException(String message, Throwable cause) {
        super(message, cause);
    }
}
