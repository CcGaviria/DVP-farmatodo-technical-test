package com.gaviria.farmatodo_user_service.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gaviria.farmatodo_user_service.dto.UserResponse;
import com.gaviria.farmatodo_user_service.models.User;
import com.gaviria.farmatodo_user_service.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(UUID id, User user) {
        User userToUpdate = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        return userRepository.save(userToUpdate);
    }

    public List<UserResponse> getUsers() {
        return userRepository.findAll()
                .stream()
                .map( user -> new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail())).collect(Collectors.toList());
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
    }
    
}
