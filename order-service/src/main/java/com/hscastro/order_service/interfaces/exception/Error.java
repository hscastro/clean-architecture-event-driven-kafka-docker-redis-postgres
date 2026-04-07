package com.hscastro.order_service.interfaces.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Error {
    private int status;
    private String message;
    private LocalDateTime timestamp;
}
