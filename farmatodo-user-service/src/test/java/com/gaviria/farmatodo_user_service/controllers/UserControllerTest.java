package com.gaviria.farmatodo_user_service.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.gaviria.farmatodo_user_service.services.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaviria.farmatodo_user_service.dto.UserRequest;
import com.gaviria.farmatodo_user_service.dto.UserResponse;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }


    @Test
    void testCreateUser() throws Exception {
        UserResponse user = new UserResponse(UUID.randomUUID(), "Cristian", "Gaviria", "Cristian@gaviria.org");

        when(userService.createUser(any(UserRequest.class))).thenReturn(user);

        String jsonRequest = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/api/users")
                .contentType(APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Cristian")))
                .andExpect(jsonPath("$.lastName", is("Gaviria")))
                .andExpect(jsonPath("$.email", is("Cristian@gaviria.org")));
    }

    @Test
    void testUpdateUser() throws Exception {
        UUID userId = UUID.randomUUID();
        UserResponse user = new UserResponse(userId, "Cristian Camilo", "Gaviria Ovalle", "Cristian@gaviria.org");

        when(userService.updateUser(any(UUID.class), any(UserRequest.class))).thenReturn(user);

        String jsonRequest = objectMapper.writeValueAsString(user);

        mockMvc.perform(put("/api/users/" + userId)
                .contentType(APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Cristian Camilo"))
                .andExpect(jsonPath("$.lastName").value("Gaviria Ovalle"))
                .andExpect(jsonPath("$.email").value("Cristian@gaviria.org"));
    }

    @Test
    void testGetUsers() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }   

    @Test
    void testGetUserById() throws Exception {
        UUID userId = UUID.randomUUID();
        UserResponse user = new UserResponse(userId, "Cristian", "Gaviria", "cristian@gaviria.org");

        when(userService.getUserById(any(UUID.class))).thenReturn(user);

        mockMvc.perform(get("/api/users/" + userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Cristian"));
    }
}
