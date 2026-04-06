package com.hscastro.example.order_service.interfaces.mapper;

import com.hscastro.example.order_service.domain.model.Order;
import com.hscastro.example.order_service.interfaces.dto.request.OrderRequestDTO;
import com.hscastro.example.order_service.interfaces.dto.response.OrderResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(OrderRequestDTO dto);
    OrderResponseDTO toDTO(Order order);
}
