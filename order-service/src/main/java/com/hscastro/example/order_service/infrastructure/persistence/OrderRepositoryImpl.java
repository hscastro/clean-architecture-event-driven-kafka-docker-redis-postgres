package com.hscastro.example.order_service.infrastructure.persistence;

import com.hscastro.example.order_service.domain.model.Order;
import com.hscastro.example.order_service.domain.repository.OrderRepository;
import com.hscastro.example.order_service.interfaces.dto.request.OrderRequestDTO;
import com.hscastro.example.order_service.interfaces.dto.response.OrderResponseDTO;
import com.hscastro.example.order_service.interfaces.exception.OrderValidationException;
import com.hscastro.example.order_service.interfaces.mapper.OrderMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final JpaOrderRepository jpaOrderRepository;

    private final OrderMapper orderMapper;


    public OrderRepositoryImpl(JpaOrderRepository jpaOrderRepository, OrderMapper orderMapper) {
        this.jpaOrderRepository = jpaOrderRepository;
        this.orderMapper = orderMapper;
    }


    @Override
    public OrderResponseDTO save(OrderRequestDTO orderDTO) {
        var order = orderMapper.toEntity(orderDTO);
        var newOrder = jpaOrderRepository.save(order);
        return orderMapper.toDTO(newOrder);
    }

    @Override
    public Optional<OrderResponseDTO> findById(Long id) {
        var order = jpaOrderRepository.findById(id)
                .orElseThrow(() -> new OrderValidationException("Order not found!"));

        return Optional.of(orderMapper.toDTO(order));
    }

    @Override
    public List<OrderResponseDTO> findAll() {
        return jpaOrderRepository.findAll()
                .stream()
                .map(p -> orderMapper.toDTO(p)).toList();
    }

    @Override
    public OrderResponseDTO update(Long id, OrderRequestDTO orderDTO) {
        Order orderEntity = jpaOrderRepository.findById(id)
                .orElseThrow(() -> new OrderValidationException("Order not found!"));

        var order = orderMapper.toEntity(orderDTO);
        orderEntity.setProduct(order.getProduct());
        orderEntity.setQuantity(order.getQuantity());

        jpaOrderRepository.save(orderEntity);

        return orderMapper.toDTO(orderEntity);
    }


    @Override
    public void deleteById(Long id) {
        jpaOrderRepository.findById(id)
                .orElseThrow(() -> new OrderValidationException("Order not found!"));
        jpaOrderRepository.deleteById(id);
    }
}
