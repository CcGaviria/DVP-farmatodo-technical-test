package com.gaviria.farmatodo_ticket_service.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.gaviria.farmatodo_ticket_service.enums.TicketStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketResponse {
    private UUID id;
    private String description;
    private UUID userId;
    private TicketStatus status;
    private LocalDateTime createdAt;
}
