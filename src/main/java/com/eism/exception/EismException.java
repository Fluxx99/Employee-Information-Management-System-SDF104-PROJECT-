package com.eism.exception;

public class EismException extends RuntimeException {
    public EismException(String message, Throwable cause) {
        super(message, cause);
    }
}
