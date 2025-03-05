package com.gaviria.farmatodo_user_service.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaviria.farmatodo_user_service.dto.UserRequest;
import com.gaviria.farmatodo_user_service.dto.UserResponse;
import com.gaviria.farmatodo_user_service.mappers.UserMapper;
import com.gaviria.farmatodo_user_service.models.User;
import com.gaviria.farmatodo_user_service.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserResponse createUser(UserRequest user) {
        User newUser = userMapper.toEntity(user);
        userRepository.save(newUser);
        return userMapper.toResponse(newUser);
    }

    @Transactional
    public UserResponse updateUser(UUID id, UserRequest user) {
        User userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        userRepository.save(userToUpdate);
        return userMapper.toResponse(userToUpdate);
    }

    public List<UserResponse> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail()))
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
        return userMapper.toResponse(user);
    }

}
