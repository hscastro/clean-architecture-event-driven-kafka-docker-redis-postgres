package com.hscastro.example.order_service.infrastructure.kafka.producer;

import com.hscastro.example.order_service.domain.model.Event;
import com.hscastro.example.order_service.interfaces.mapper.OrderMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderKafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final OrderMapper orderMapper;

    public OrderKafkaProducer(KafkaTemplate<String, Object> kafkaTemplate, OrderMapper orderMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.orderMapper = orderMapper;
    }

    public void send(String topic, Event event) {
        kafkaTemplate.send(topic, event);
    }
}
