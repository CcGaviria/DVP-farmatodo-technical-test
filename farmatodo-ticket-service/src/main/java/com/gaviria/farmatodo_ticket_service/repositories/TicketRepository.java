package com.gaviria.farmatodo_ticket_service.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gaviria.farmatodo_ticket_service.enums.TicketStatus;
import com.gaviria.farmatodo_ticket_service.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    Page<Ticket> findAll(Pageable pageable);

    Page<Ticket> findByUserId(UUID userId, Pageable pageable);

    Page<Ticket> findByStatus(TicketStatus status, Pageable pageable);

    Page<Ticket> findByUserIdAndStatus(UUID userId, TicketStatus status, Pageable pageable);
    
}
