package com.hscastro.example.order_service.domain.model;


import com.hscastro.example.order_service.domain.enums.EOrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String transactionId;
    private String orderId;
    private String payload;
    private EOrderStatus status;

    @OneToMany
    private List<History> eventHistory;
    private LocalDateTime createdAt;

}
