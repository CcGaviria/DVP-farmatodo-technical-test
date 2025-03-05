package com.gaviria.farmatodo_ticket_service.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gaviria.farmatodo_ticket_service.dto.TicketRequest;
import com.gaviria.farmatodo_ticket_service.dto.TicketResponse;
import com.gaviria.farmatodo_ticket_service.enums.TicketStatus;
import com.gaviria.farmatodo_ticket_service.mappers.TicketMapper;
import com.gaviria.farmatodo_ticket_service.models.Ticket;
import com.gaviria.farmatodo_ticket_service.repositories.TicketRepository;

import lombok.AllArgsConstructor;

import jakarta.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class TicketService {
    
    private final TicketRepository ticketRepository;

    private final TicketMapper ticketMapper;

    public TicketResponse createTicket(TicketRequest ticket) {
        Ticket newTicket = ticketMapper.toEntity(ticket);
        ticketRepository.save(newTicket); 
        return ticketMapper.toResponse(newTicket);
    }

    public TicketResponse updateTicket(UUID id, TicketRequest ticket) {
        Ticket ticketToUpdate = ticketRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ticket no encontrado con ID: " + id));
        ticketToUpdate.setDescription(ticket.getDescription());
        ticketToUpdate.setStatus(ticket.getStatus());
        ticketToUpdate.setUserId(ticket.getUserId());
        ticketRepository.save(ticketToUpdate);
        return ticketMapper.toResponse(ticketToUpdate);
    }

    public void deleteTicket(UUID id) {
        ticketRepository.deleteById(id);
    }

    public TicketResponse getTicketById(UUID id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ticket no encontrado con ID: " + id));
        return ticketMapper.toResponse(ticket);
    }

    public Page<TicketResponse> getTickets(UUID userId, TicketStatus status, Pageable pageable) {
        if( userId != null && status != null) {
            return ticketRepository.findByUserIdAndStatus(userId, status, pageable)
                    .map(ticketMapper::toResponse);
        } else if( userId != null) {
            return ticketRepository.findByUserId(userId, pageable)
                    .map(ticketMapper::toResponse);
        } else if( status != null) {
            return ticketRepository.findByStatus(status, pageable)
                    .map(ticketMapper::toResponse);
        }
        return ticketRepository.findAll(pageable)
                .map(ticketMapper::toResponse);
    }

    
}
