package com.example.api.exception;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException() {
        super("Address not found.");
    }

}
