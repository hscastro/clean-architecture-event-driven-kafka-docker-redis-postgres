package com.hscastro.example.order_service.application.usecase;

import com.hscastro.example.order_service.domain.model.Event;
import com.hscastro.example.order_service.domain.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetEventUseCase {

    private final EventRepository eventRepository;

    public GetEventUseCase(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Optional<Event> execute(UUID id){
        return eventRepository.findById(id);
    }

}
