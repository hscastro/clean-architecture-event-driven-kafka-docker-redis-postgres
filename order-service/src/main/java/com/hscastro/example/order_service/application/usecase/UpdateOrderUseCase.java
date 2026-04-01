package com.hscastro.example.order_service.application.usecase;

import com.hscastro.example.order_service.application.dto.OrderRequestDTO;
import com.hscastro.example.order_service.application.dto.OrderResponseDTO;
import com.hscastro.example.order_service.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateOrderUseCase {

    private final OrderRepository repository;

    public UpdateOrderUseCase(OrderRepository repository) {
        this.repository = repository;
    }

    public OrderResponseDTO execute(Long id, OrderRequestDTO data) {
        return repository.update(id, data);
    }
}
