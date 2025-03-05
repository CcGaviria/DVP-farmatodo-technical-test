package com.gaviria.farmatodo_ticket_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.gaviria.farmatodo_ticket_service.clients")
public class FarmatodoTicketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmatodoTicketServiceApplication.class, args);
	}

}
