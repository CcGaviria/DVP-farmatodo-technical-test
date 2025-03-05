package com.gaviria.farmatodo_ticket_service.dto;

import java.util.UUID;

import com.gaviria.farmatodo_ticket_service.enums.TicketStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketRequest {
    private String description;
    private UUID userId;
    private TicketStatus status;
}
