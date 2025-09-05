package com.example.yandexnewsindexer.service;

import com.example.yandexnewsindexer.dto.AuthRequest;
import com.example.yandexnewsindexer.dto.AuthResponse;
import com.example.yandexnewsindexer.entity.UserEntity;
import com.example.yandexnewsindexer.repository.UserRepository;
import com.example.yandexnewsindexer.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public void register(AuthRequest request) {
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    public AuthResponse login(AuthRequest request) {
        UserEntity user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        UserDetails principal = org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(List.of())
                .build();

        String token = jwtUtils.generateToken(String.valueOf(principal));
        return new AuthResponse(token);
    }
}
