package com.hscastro.example.order_service.domain.repository;

import com.hscastro.example.order_service.interfaces.dto.request.OrderRequestDTO;
import com.hscastro.example.order_service.interfaces.dto.response.OrderResponseDTO;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository {
    OrderResponseDTO save(OrderRequestDTO orderDTO);
    Optional<OrderResponseDTO> findById(Long id);
    List<OrderResponseDTO> findAll();
    void deleteById(Long id);
    OrderResponseDTO update(Long id, OrderRequestDTO orderDTO);
}
