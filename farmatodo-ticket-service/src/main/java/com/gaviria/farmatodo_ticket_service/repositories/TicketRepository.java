package com.gaviria.farmatodo_ticket_service.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gaviria.farmatodo_ticket_service.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    Page<Ticket> findAll(Pageable pageable);
    
}
