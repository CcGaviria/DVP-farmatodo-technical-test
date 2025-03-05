package com.gaviria.controllers;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaviria.farmatodo_ticket_service.controllers.TicketController;
import com.gaviria.farmatodo_ticket_service.dto.TicketRequest;
import com.gaviria.farmatodo_ticket_service.dto.TicketResponse;
import com.gaviria.farmatodo_ticket_service.enums.TicketStatus;
import com.gaviria.farmatodo_ticket_service.services.TicketService;

@ExtendWith(MockitoExtension.class)
public class TicketControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private TicketController ticketController;

    @Mock
    private TicketService ticketService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(ticketController).build();
    }

    @Test
    void testCreateTicket() throws Exception {
        TicketResponse ticket = new TicketResponse(UUID.randomUUID(), "Nuevo ticket de compra", UUID.randomUUID(),
                TicketStatus.ABIERTO);

        when(ticketService.createTicket(any(TicketRequest.class))).thenReturn(ticket);

        String jsonRequest = objectMapper.writeValueAsString(ticket);

        mockMvc.perform(post("/api/tickets")
                .contentType(APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(ticket.getId().toString())))
                .andExpect(jsonPath("$.description", is(ticket.getDescription())))
                .andExpect(jsonPath("$.userId", is(ticket.getUserId().toString())))
                .andExpect(jsonPath("$.status", is(ticket.getStatus().toString())));
    }

    @Test
    void testUpdateTicket() throws Exception {
        TicketResponse updatedTicket = new TicketResponse(UUID.randomUUID(), "Ticket de compra actualizado",
                UUID.randomUUID(), TicketStatus.CERRADO);

        when(ticketService.updateTicket(any(UUID.class), any(TicketRequest.class))).thenReturn(updatedTicket);

        String jsonRequest = objectMapper.writeValueAsString(updatedTicket);

        mockMvc.perform(put("/api/tickets/{id}", updatedTicket.getId())
                .contentType(APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(updatedTicket.getId().toString())))
                .andExpect(jsonPath("$.description", is(updatedTicket.getDescription())))
                .andExpect(jsonPath("$.userId", is(updatedTicket.getUserId().toString())))
                .andExpect(jsonPath("$.status", is(updatedTicket.getStatus().toString())));
    }

    @Test
    void testDeleteTicket() throws Exception {
        UUID ticketId = UUID.randomUUID();

        mockMvc.perform(delete("/api/tickets/{id}", ticketId))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetTicket() throws Exception {
        TicketResponse ticket = new TicketResponse(UUID.randomUUID(), "Nuevo ticket de compra", UUID.randomUUID(),
                TicketStatus.ABIERTO);

        when(ticketService.getTicketById(any(UUID.class))).thenReturn(ticket);

        mockMvc.perform(get("/api/tickets/{id}", ticket.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(ticket.getId().toString())))
                .andExpect(jsonPath("$.description", is(ticket.getDescription())))
                .andExpect(jsonPath("$.userId", is(ticket.getUserId().toString())))
                .andExpect(jsonPath("$.status", is(ticket.getStatus().toString())));
    }

}
