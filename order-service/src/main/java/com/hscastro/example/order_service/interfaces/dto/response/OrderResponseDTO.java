package com.hscastro.example.order_service.interfaces.dto.response;

import java.util.UUID;

public record OrderResponseDTO(
        UUID id,
        String product,
        Integer quantity,
        double price,
        String status
) {
}

