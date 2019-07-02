package com.coditas.test.exception;

public class InvalidOrderException extends RuntimeException {

    // thrown when all the ingredients are excluded from an item in order
    public InvalidOrderException(String errorMessage) {
        super(errorMessage);
    }
}
