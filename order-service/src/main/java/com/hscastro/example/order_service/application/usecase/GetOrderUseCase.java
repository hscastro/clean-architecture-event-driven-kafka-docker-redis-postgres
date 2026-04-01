package com.hscastro.example.order_service.application.usecase;

import com.hscastro.example.order_service.application.dto.OrderRequestDTO;
import com.hscastro.example.order_service.application.dto.OrderResponseDTO;
import com.hscastro.example.order_service.domain.model.OrderEvent;
import com.hscastro.example.order_service.domain.repository.OrderRepository;
import com.hscastro.example.order_service.infrastructure.kafka.consumer.OrderKafkaConsumer;
import com.hscastro.example.order_service.infrastructure.redis.OrderCache;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class GetOrderUseCase {

    private final OrderRepository repository;
    private final OrderKafkaConsumer consumer;
    private final OrderCache cache;

    public GetOrderUseCase(OrderRepository repository, OrderKafkaConsumer consumer, OrderCache cache) {
        this.repository = repository;
        this.consumer = consumer;
        this.cache = cache;
    }

    public OrderResponseDTO execute(Long id) {
        OrderResponseDTO cached = cache.get(id);
        if (cached != null){
            System.out.println("Buscou no cache...");
            return cached;
        }

        OrderResponseDTO orderResponse = repository.findById(id).get();
        OrderRequestDTO orderRequest = getOrderRequestDTO(orderResponse);

        cache.save(orderRequest);

        return orderResponse;
    }

    private static @NonNull OrderRequestDTO getOrderRequestDTO(OrderResponseDTO orderResponse) {
        OrderRequestDTO orderRequest = new OrderRequestDTO(
                orderResponse.id(),
                orderResponse.product(),
                orderResponse.quantity(),
                orderResponse.status()
        );
        return orderRequest;
    }

//    public GetOrderUseCase(OrderRepository repository, OrderKafkaConsumer consumer) {
//        this.repository = repository;
//        this.consumer = consumer;
//    }

    public OrderResponseDTO executeTTR(Long id) {
        OrderResponseDTO dto = repository.findById(id).get();

        OrderEvent event = new OrderEvent(
                dto.id(),
                dto.product(),
                dto.quantity(),
                dto.status()
        );

        if (Objects.nonNull(event)){
            consumer.consumer(event);
        }
        return dto;
    }
}
