package com.gaviria.farmatodo_ticket_service.controllers;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaviria.farmatodo_ticket_service.dto.TicketRequest;
import com.gaviria.farmatodo_ticket_service.models.Ticket;
import com.gaviria.farmatodo_ticket_service.services.TicketService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/tickets")
@AllArgsConstructor
@Tag(name = "Tickets", description = "Operaciones CRUD de Tickets")
@SecurityRequirement(name = "BearerAuth")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    @Operation(summary = "Crear un ticket")
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketRequest ticket) {
        return ResponseEntity.ok(ticketService.createTicket(ticket));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un ticket")
    public ResponseEntity<Ticket> updateTicket(@PathVariable UUID id, @RequestBody TicketRequest ticket) {
        return ResponseEntity.ok(ticketService.updateTicket(id, ticket));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un ticket")
    public ResponseEntity<Void> deleteTicket(@PathVariable UUID id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un ticket por ID")
    public ResponseEntity<Ticket> getTicketById(@PathVariable UUID id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @GetMapping
    @Operation(summary = "Obtener todos los tickets")
    public ResponseEntity<Page<Ticket>> getTickets(Pageable pageable) {
        return ResponseEntity.ok(ticketService.getTickets(pageable));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
}
