package com.hscastro.example.order_service.infrastructure.persistence;

import com.hscastro.example.order_service.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaOrderRepository extends JpaRepository<Order, Long> {
}
