package com.hscastro.example.order_service.application.dto;

public record OrderRequestDTO(
        Long id,
        String product,
        Integer quantity,
        String status
){
}
