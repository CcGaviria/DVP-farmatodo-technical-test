package com.gaviria.farmatodo_ticket_service.clients;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.gaviria.farmatodo_ticket_service.dto.UserResponse;

@FeignClient(name = "farmatodo-user-service", url = "${farmatodo.user-service.url}")
public interface UserClient {

    @GetMapping("/api/users/{id}")
    UserResponse getUserById(@PathVariable UUID id);

    @GetMapping("/api/auth/validate-token")
    String validateToken(@RequestHeader("Authorization") String token);
    
}
