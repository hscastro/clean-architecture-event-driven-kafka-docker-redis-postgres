package com.hscastro.example.order_service.infrastructure.persistence;

import com.hscastro.example.order_service.domain.model.Event;
import com.hscastro.example.order_service.domain.repository.EventRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaEventRepository extends JpaRepository<Event, String> {
}
