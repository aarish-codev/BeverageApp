package com.coditas.app;

public class InvalidOrderException extends RuntimeException {

    public InvalidOrderException(String errorMessage) {
        super(errorMessage);
    }
}
