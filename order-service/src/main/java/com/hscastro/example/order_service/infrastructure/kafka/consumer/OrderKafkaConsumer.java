package com.hscastro.example.order_service.infrastructure.kafka.consumer;

import com.hscastro.example.order_service.domain.model.Event;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderKafkaConsumer {

    @KafkaListener(topics = "order-created", groupId = "product-group")
    public void consumer(Event event){
        System.out.println("Pedido recebido "+event);
    }
}
