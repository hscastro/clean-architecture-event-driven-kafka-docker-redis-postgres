package com.hscastro.example.order_service.application.usecase;

import com.hscastro.example.order_service.application.dto.OrderRequestDTO;
import com.hscastro.example.order_service.application.dto.OrderResponseDTO;
import com.hscastro.example.order_service.domain.model.Order;
import com.hscastro.example.order_service.domain.model.OrderEvent;
import com.hscastro.example.order_service.domain.repository.OrderRepository;
import com.hscastro.example.order_service.infrastructure.kafka.producer.OrderKafkaProducer;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class CreateOrderUseCase {

    private final OrderRepository repository;
    private final OrderKafkaProducer producer;


    public CreateOrderUseCase(OrderRepository repository, OrderKafkaProducer producer) {
        this.repository = repository;
        this.producer = producer;
    }

    public OrderResponseDTO execute(OrderRequestDTO orderRequestDTO) {
        OrderResponseDTO dto = repository.save(orderRequestDTO);
        OrderEvent event = new OrderEvent(
                dto.id(),
                dto.product(),
                dto.quantity(),
                dto.status()
        );

        // Evento para o Kafka
        if(Objects.nonNull(dto.id())){
            producer.send("order-created", event);
        }

        return dto;
    }
}
