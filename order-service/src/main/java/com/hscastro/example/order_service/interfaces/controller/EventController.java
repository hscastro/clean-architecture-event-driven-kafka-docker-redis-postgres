package com.hscastro.example.order_service.interfaces.controller;

import com.hscastro.example.order_service.application.usecase.GetEventUseCase;
import com.hscastro.example.order_service.application.usecase.ListEventsUseCase;
import com.hscastro.example.order_service.domain.model.Event;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;



@Slf4j
@RestController
@RequestMapping("events")
public class EventController {

    private final ListEventsUseCase eventsUseCase;
    private final GetEventUseCase getEventUseCase;


    public EventController(ListEventsUseCase eventsUseCase, GetEventUseCase getEventUseCase) {
        this.eventsUseCase = eventsUseCase;
        this.getEventUseCase = getEventUseCase;
    }

    @Operation(summary = "List Events")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação"),
            @ApiResponse(responseCode = "422", description = "Erro no processamento da requição ")
    })
    @GetMapping
    public ResponseEntity<List<Event>> listOrders(){
        var list = eventsUseCase.execute();
        log.info("Finding events with data {}", list);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Get Event")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação"),
            @ApiResponse(responseCode = "422", description = "Erro no processamento da requição ")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Event>> getEvent(@PathVariable("id") UUID id){
        var event = getEventUseCase.execute(id);
        log.info("Finding event to id {} with data {}", id, event);
        return ResponseEntity.ok(event);
    }
}
