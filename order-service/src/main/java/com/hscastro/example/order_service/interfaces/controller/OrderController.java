package com.hscastro.example.order_service.interfaces.controller;

import com.hscastro.example.order_service.application.dto.OrderRequestDTO;
import com.hscastro.example.order_service.application.dto.OrderResponseDTO;
import com.hscastro.example.order_service.application.usecase.CreateOrderUseCase;
import com.hscastro.example.order_service.application.usecase.DeleteOrderUseCase;
import com.hscastro.example.order_service.application.usecase.GetOrderUseCase;
import com.hscastro.example.order_service.application.usecase.ListOrdersUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final ListOrdersUseCase listOrdersUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final DeleteOrderUseCase deleteOrderUseCase;

    public OrderController(CreateOrderUseCase createOrderUseCase, ListOrdersUseCase listOrdersUseCase, GetOrderUseCase getOrderUseCase, DeleteOrderUseCase deleteOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
        this.listOrdersUseCase = listOrdersUseCase;
        this.getOrderUseCase = getOrderUseCase;
        this.deleteOrderUseCase = deleteOrderUseCase;
    }


    @Operation(summary = "Create Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação"),
            @ApiResponse(responseCode = "422", description = "Erro no processamento da requição ")
    })
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO requestDTO){
        OrderResponseDTO responseDTO = createOrderUseCase.execute(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }


    @Operation(summary = "List Orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação"),
            @ApiResponse(responseCode = "422", description = "Erro no processamento da requição ")
    })
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> listOrders(){
        return ResponseEntity.ok(listOrdersUseCase.execute());
    }

    @Operation(summary = "Get Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação"),
            @ApiResponse(responseCode = "422", description = "Erro no processamento da requição ")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable("id") Long id){
        OrderResponseDTO orderResponseDTO = getOrderUseCase.execute(id);
        return ResponseEntity.ok(orderResponseDTO);
    }

    @Operation(summary = "delete Order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação"),
            @ApiResponse(responseCode = "422", description = "Erro no processamento da requição ")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteOrderUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
