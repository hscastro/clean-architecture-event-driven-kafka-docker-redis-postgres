package com.hscastro.example.order_service.interfaces.exception;

public class OrderValidationException extends RuntimeException {

    public OrderValidationException(String message) {
        super(message);
    }
}
