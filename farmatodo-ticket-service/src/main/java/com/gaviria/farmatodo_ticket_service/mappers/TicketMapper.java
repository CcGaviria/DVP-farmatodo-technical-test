package com.gaviria.farmatodo_ticket_service.mappers;

import org.springframework.stereotype.Component;

import com.gaviria.farmatodo_ticket_service.dto.TicketRequest;
import com.gaviria.farmatodo_ticket_service.dto.TicketResponse;
import com.gaviria.farmatodo_ticket_service.models.Ticket;

@Component
public class TicketMapper {
    public Ticket toEntity(TicketRequest ticketRequest) {
        Ticket ticket = new Ticket();
        ticket.setDescription(ticketRequest.getDescription());
        ticket.setStatus(ticketRequest.getStatus());
        ticket.setUserId(ticketRequest.getUserId());
        return ticket;
    }

    public TicketResponse toResponse(Ticket ticket) {
        return new TicketResponse(ticket.getId(), ticket.getDescription(), ticket.getUserId(), ticket.getStatus());
    }
}
