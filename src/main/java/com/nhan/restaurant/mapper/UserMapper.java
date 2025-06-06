package com.nhan.restaurant.mapper;

import com.nhan.restaurant.dto.UserDTO;
import com.nhan.restaurant.entity.User;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setRole(user.getRole().name());
        return dto;
    }

    public static void updateUserFromDTO(User user, UserDTO dto) {
        user.setName(dto.getName());
        user.setPhoneNumber(dto.getPhoneNumber());
    }
}
