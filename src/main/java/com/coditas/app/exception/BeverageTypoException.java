package com.coditas.app.exception;

public class BeverageTypoException extends RuntimeException {

    // Thrown when there is typing error in order or the item is not present in DataLoader
    public BeverageTypoException(String errorMessage) {
        super(errorMessage);
    }
}
