package com.hscastro.example.order_service.application.usecase;

import com.hscastro.example.order_service.application.dto.OrderResponseDTO;
import com.hscastro.example.order_service.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListOrdersUseCase {

    private final OrderRepository repository;
    //private final KafkaProducer producer;

//    public CreateOrderUseCase(OrderRepository repository, KafkaProducer producer) {
//        this.repository = repository;
//        this.producer = producer;
//    }

    public ListOrdersUseCase(OrderRepository repository) {
        this.repository = repository;
    }

    public List<OrderResponseDTO> execute() {
        List<OrderResponseDTO> list = repository.findAll();
        return list;
    }
}
