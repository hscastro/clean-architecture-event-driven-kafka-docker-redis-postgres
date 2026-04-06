package com.hscastro.example.order_service.infrastructure.persistence;

import com.hscastro.example.order_service.domain.model.Event;
import com.hscastro.example.order_service.domain.repository.EventRepository;
import com.hscastro.example.order_service.interfaces.exception.EventValidationException;
import com.hscastro.example.order_service.interfaces.exception.OrderValidationException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class EventRepositoryImpl implements EventRepository {

    private final JpaEventRepository eventRepository;

    public EventRepositoryImpl(JpaEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event save(Event event){
        return eventRepository.save(event);
    }

    @Override
    public Event update(UUID id, Event event) {
        var eventDB = eventRepository.findById(id.toString())
                .orElseThrow(() -> new EventValidationException("Event not found!"));

        eventDB.setOrderId(event.getOrderId());
        eventDB.setTransactionId(event.getTransactionId());
        eventDB.setPayload(event.getPayload());
        eventDB.setStatus(event.getStatus());
        eventDB.setCreatedAt(event.getCreatedAt());

        eventRepository.save(eventDB);

        return eventDB;
    }

    @Override
    public List<Event> findAllByOrderByCreatedDesc() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> findTopByOrderIdOrderByCreatedDesc(String orderId) {
        var event = eventRepository.findById(orderId)
                .orElseThrow(() -> new EventValidationException("Event not found!"));

        return Optional.of(event);
    }

    @Override
    public Optional<Event> findById(UUID id) {
        var event = eventRepository.findById(id.toString())
                .orElseThrow(() -> new EventValidationException("Event not found!"));

        return Optional.of(event);
    }

    @Override
    public Optional<Event> findTopByTransactionIdOrderByCreatedDesc(String transactionId) {
        var event = eventRepository.findById(transactionId)
                .orElseThrow(() -> new EventValidationException("Event not found!"));

        return Optional.of(event);
    }

    @Override
    public void delete(UUID id) {
        var obj = eventRepository.findById(id.toString())
                    .orElseThrow(() -> new EventValidationException("Event not found!"));

        eventRepository.delete(obj);
    }
}
