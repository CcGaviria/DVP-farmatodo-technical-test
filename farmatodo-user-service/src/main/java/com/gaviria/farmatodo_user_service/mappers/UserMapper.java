package com.gaviria.farmatodo_user_service.mappers;

import org.springframework.stereotype.Component;

import com.gaviria.farmatodo_user_service.dto.UserRequest;
import com.gaviria.farmatodo_user_service.dto.UserResponse;
import com.gaviria.farmatodo_user_service.models.User;

@Component
public class UserMapper {
    public User toEntity(UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        return user;
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
    }
}