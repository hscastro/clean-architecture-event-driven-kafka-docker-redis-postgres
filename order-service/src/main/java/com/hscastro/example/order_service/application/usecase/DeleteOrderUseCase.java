package com.hscastro.example.order_service.application.usecase;

import com.hscastro.example.order_service.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteOrderUseCase {

    private final OrderRepository orderRepository;

    public DeleteOrderUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void execute(Long id){
        orderRepository.deleteById(id);
    }
}
