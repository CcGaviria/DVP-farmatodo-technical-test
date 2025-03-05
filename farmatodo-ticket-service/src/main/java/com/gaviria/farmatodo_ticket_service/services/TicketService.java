package com.gaviria.farmatodo_ticket_service.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gaviria.farmatodo_ticket_service.models.Ticket;
import com.gaviria.farmatodo_ticket_service.repositories.TicketRepository;

import lombok.AllArgsConstructor;

import jakarta.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class TicketService {
    
    private final TicketRepository ticketRepository;

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(UUID id, Ticket ticket) {
        Ticket ticketToUpdate = ticketRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ticket no encontrado con ID: " + id));
        ticketToUpdate.setDescription(ticket.getDescription());
        ticketToUpdate.setStatus(ticket.getStatus());
        return ticketRepository.save(ticketToUpdate);
    }

    public void deleteTicket(UUID id) {
        ticketRepository.deleteById(id);
    }

    public Ticket getTicketById(UUID id) {
        return ticketRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ticket no encontrado con ID: " + id));
    }

    public Page<Ticket> getTickets(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }

    
}
