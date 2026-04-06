package com.hscastro.example.order_service.application.usecase;

import com.hscastro.example.order_service.domain.repository.OrderRepository;
import com.hscastro.example.order_service.infrastructure.messaging.consumer.OrderKafkaConsumer;
import com.hscastro.example.order_service.infrastructure.redis.OrderCache;
import com.hscastro.example.order_service.interfaces.dto.request.OrderRequestDTO;
import com.hscastro.example.order_service.interfaces.dto.response.OrderResponseDTO;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

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
                orderResponse.product(),
                orderResponse.quantity(),
                orderResponse.price(),
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

//        Event event = new Event(
//                dto.id(),
//                dto.product(),
//                dto.quantity(),
//                dto.status()
//        );
//
//        if (Objects.nonNull(event)){
//            consumer.consumer(event);
//        }
        return dto;
    }
}
