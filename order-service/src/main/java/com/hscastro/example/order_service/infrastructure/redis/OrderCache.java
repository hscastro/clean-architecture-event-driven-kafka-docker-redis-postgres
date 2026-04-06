package com.hscastro.example.order_service.infrastructure.redis;

import com.hscastro.example.order_service.domain.model.Order;
import com.hscastro.example.order_service.interfaces.dto.request.OrderRequestDTO;
import com.hscastro.example.order_service.interfaces.dto.response.OrderResponseDTO;
import com.hscastro.example.order_service.interfaces.mapper.OrderMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;


@Component
public class OrderCache {

    private final RedisTemplate<String, Object> redisTemplate;
    private final OrderMapper orderMapper;

    public OrderCache(RedisTemplate<String, Object> redisTemplate, OrderMapper orderMapper) {
        this.redisTemplate = redisTemplate;
        this.orderMapper = orderMapper;
    }

    public void save(OrderRequestDTO dto){
        var order = orderMapper.toEntity(dto);
        String cacheKey = "order:"+order;

        redisTemplate.opsForValue().set(
                cacheKey,
                order,
                Duration.ofMinutes(10));
    }

    public OrderResponseDTO get(Long id){
        String cacheKey = "order:"+id;
        var order = (Order) redisTemplate.opsForValue().get(cacheKey);
        return order!= null ? orderMapper.toDTO(order): null;
    }
}
