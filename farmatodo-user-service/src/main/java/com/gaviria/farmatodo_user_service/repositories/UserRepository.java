package com.gaviria.farmatodo_user_service.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gaviria.farmatodo_user_service.models.User;

public interface UserRepository extends JpaRepository<User, UUID> {


    
}
