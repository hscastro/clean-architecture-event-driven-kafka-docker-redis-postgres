package com.hscastro.example.order_service.infrastructure.kafka.consumer;

import com.hscastro.example.order_service.application.dto.OrderResponseDTO;
import com.hscastro.example.order_service.domain.model.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderKafkaConsumer {

    @KafkaListener(topics = "order-created", groupId = "product-group")
    public void consumer(OrderEvent event){
        System.out.println("Pedido recebido "+event.getProduct());
    }
}
