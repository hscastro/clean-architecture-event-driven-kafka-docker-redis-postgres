package com.hscastro.example.order_service.infrastructure.messaging.producer;

import com.hscastro.example.order_service.domain.model.Event;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderKafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public OrderKafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String topic, Event event) {
        kafkaTemplate.send(topic, event);
    }
}
