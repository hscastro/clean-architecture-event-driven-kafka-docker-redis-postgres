package com.hscastro.order_service.interfaces.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class OrderControllerAdvance {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(OrderNotFoundException.class)
    public Error handlerOrderNotFound(OrderNotFoundException orderNotFoundException){
        return Error.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message("Order not found!")
                .timestamp(LocalDateTime.now())
                .build();
    }
}
