package com.hscastro.example.order_service.application.usecase;

import com.hscastro.example.order_service.domain.enums.EOrderStatus;
import com.hscastro.example.order_service.domain.model.Event;
import com.hscastro.example.order_service.domain.repository.EventRepository;
import com.hscastro.example.order_service.domain.repository.OrderRepository;
import com.hscastro.example.order_service.infrastructure.kafka.producer.OrderKafkaProducer;
import com.hscastro.example.order_service.interfaces.dto.request.OrderRequestDTO;
import com.hscastro.example.order_service.interfaces.dto.response.OrderResponseDTO;
import com.hscastro.example.order_service.interfaces.utils.JsonUtil;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


@Service
public class CreateOrderUseCase {

    private static final String TRANSACTION_ID_PATTERN = "%s_%s";

    private final OrderRepository repository;
    private final OrderKafkaProducer producer;
    private final EventRepository eventRepository;
    private final JsonUtil jsonUtil;


    public CreateOrderUseCase(OrderRepository repository, OrderKafkaProducer producer, EventRepository eventRepository, JsonUtil jsonUtil) {
        this.repository = repository;
        this.producer = producer;
        this.eventRepository = eventRepository;
        this.jsonUtil = jsonUtil;
    }

    public OrderResponseDTO execute(OrderRequestDTO orderRequestDTO) {
        OrderResponseDTO dto = repository.save(orderRequestDTO);

        Event event = Event.builder()
                .orderId(dto.id().toString())
                .transactionId(String.format(TRANSACTION_ID_PATTERN,
                        Instant.now().toEpochMilli(), UUID.randomUUID()))
                .payload(jsonUtil.toJson(dto))
                .status(EOrderStatus.PENDING)
                .createdAt(LocalDateTime.now())
        .build();

        // Evento para o Kafka
        if(Objects.nonNull(dto.id())){
            producer.send("order-created", event);
            eventRepository.save(event);
        }
        return dto;
    }
}
