package com.example.api.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException() {
        super("Customer not found.");
    }

}
