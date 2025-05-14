package com.nhan.restaurant.service;

import com.nhan.restaurant.entity.User;
import com.nhan.restaurant.exception.AlreadyExistsException;
import com.nhan.restaurant.exception.InvalidCredentialsException;
import com.nhan.restaurant.repository.UserRepository;
import com.nhan.restaurant.security.AuthResponse;
import com.nhan.restaurant.security.JwtTokenProvider;
import com.nhan.restaurant.security.LoginRequest;
import com.nhan.restaurant.security.RegisterRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(UserRepository repo, PasswordEncoder encoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = repo;
        this.passwordEncoder = encoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new AlreadyExistsException("User already exists with email: " + request.getEmail());
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(User.Role.GUEST); // Always GUEST on registration

        userRepository.save(user);

        String token = jwtTokenProvider.generateToken(user);
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtTokenProvider.generateToken(user);
        return new AuthResponse(token);
    }
}
