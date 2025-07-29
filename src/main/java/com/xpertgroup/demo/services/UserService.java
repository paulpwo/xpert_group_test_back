package com.xpertgroup.demo.services;

import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.xpertgroup.demo.dtos.LoginResponseDto;
import com.xpertgroup.demo.dtos.UserDto;
import com.xpertgroup.demo.entities.User;
import com.xpertgroup.demo.ports.in.UserUseCase;
import com.xpertgroup.demo.ports.out.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserUseCase {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public Optional<LoginResponseDto> login(String email, String password) {
        log.info("Attempting login for user: {}", email);
        try {
            Optional<User> user = userRepository.findByEmail(email);
            if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
                log.info("Login successful for user: {}", email);
                
                String token = jwtService.generateToken(user.get());
                UserDto userDto = mapToDto(user.get());
                
                LoginResponseDto loginResponse = LoginResponseDto.builder()
                        .token(token)
                        .tokenType("Bearer")
                        .expiresIn(jwtService.getExpirationTime())
                        .user(userDto)
                        .build();
                
                return Optional.of(loginResponse);
            } else {
                log.warn("Login failed for user: {}", email);
                return Optional.empty();
            }
        } catch (Exception error) {
            log.error("Error during login for user {}: {}", email, error.getMessage());
            throw error;
        }
    }

    @Override
    public UserDto register(UserDto userDto) {
        log.info("Attempting to register user: {}", userDto.getEmail());
        try {
            // Verificar si el usuario ya existe
            if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
                log.warn("User already exists: {}", userDto.getEmail());
                throw new RuntimeException("User already exists");
            }

            // Crear nuevo usuario con contraseña encriptada
            User user = User.builder()
                    .email(userDto.getEmail())
                    .password(passwordEncoder.encode(userDto.getPassword()))
                    .build();

            User savedUser = userRepository.save(user);
            log.info("User registered successfully: {}", userDto.getEmail());
            return mapToDto(savedUser);
        } catch (Exception error) {
            log.error("Error during registration for user {}: {}", userDto.getEmail(), error.getMessage());
            throw error;
        }
    }

    private UserDto mapToDto(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .password(null) // No devolver la contraseña en el DTO
                .build();
    }
}