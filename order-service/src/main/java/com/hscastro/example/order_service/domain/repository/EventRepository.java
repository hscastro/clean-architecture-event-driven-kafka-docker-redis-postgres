package com.hscastro.example.order_service.domain.repository;

import com.hscastro.example.order_service.domain.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventRepository {
    Event save(Event event);
    Event update(UUID id, Event event);
    List<Event> findAllByOrderByCreatedDesc();
    Optional<Event> findTopByOrderIdOrderByCreatedDesc(String orderId);
    Optional<Event>  findById(UUID id);
    Optional<Event> findTopByTransactionIdOrderByCreatedDesc(String transactionId);
    void delete(UUID id);
}
