package com.nhan.restaurant.service;

import com.nhan.restaurant.dto.UserDTO;
import com.nhan.restaurant.entity.User;
import com.nhan.restaurant.exception.NotFoundException;
import com.nhan.restaurant.mapper.UserMapper;
import com.nhan.restaurant.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return UserMapper.toDTO(user);
    }

    public UserDTO getCurrentUser(UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new NotFoundException("User not found"));
        return UserMapper.toDTO(user);
    }

    public UserDTO updateCurrentUser(UserDetails userDetails, UserDTO dto) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new NotFoundException("User not found"));

        UserMapper.updateUserFromDTO(user, dto);
        return UserMapper.toDTO(userRepository.save(user));
    }
}
