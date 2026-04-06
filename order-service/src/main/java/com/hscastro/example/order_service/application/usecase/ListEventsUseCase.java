package com.hscastro.example.order_service.application.usecase;

import com.hscastro.example.order_service.domain.model.Event;
import com.hscastro.example.order_service.domain.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListEventsUseCase {

    private final EventRepository repository;

    public ListEventsUseCase(EventRepository repository) {
        this.repository = repository;
    }

    public List<Event> execute() {
        return repository.findAllByOrderByCreatedDesc();
    }

}
