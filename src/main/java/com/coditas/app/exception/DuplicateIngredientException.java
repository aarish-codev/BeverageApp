package com.coditas.app.exception;

public class DuplicateIngredientException extends RuntimeException {
    public DuplicateIngredientException(String errorMessage) {
        super(errorMessage);
    }
}
