package com.hscastro.order_service.interfaces.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class EventControllerAdvance {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EventNotFoundException.class)
    public Error handlerOrderNotFound(EventNotFoundException eventNotFoundException){
        return Error.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message("Event not found!")
                .timestamp(LocalDateTime.now())
                .build();
    }
}
