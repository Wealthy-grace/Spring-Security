package com.example.bconnectbackend.configuration.exceptions;

public class ContactAlreadyExistsException extends RuntimeException {
    public ContactAlreadyExistsException(String message) {
        super(message);
    }
}

