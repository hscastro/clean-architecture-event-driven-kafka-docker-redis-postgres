package com.hscastro.example.order_service.interfaces.exception;

public class EventValidationException extends RuntimeException {

    public EventValidationException(String message) {
        super(message);
    }
}
