package com.hscastro.example.order_service.interfaces.dto.request;


public record OrderRequestDTO(
        String product,
        Integer quantity,
        double price,
        String status
){
}

