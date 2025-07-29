package com.xpertgroup.demo.controllers;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.xpertgroup.demo.dtos.UserDto;
import com.xpertgroup.demo.ports.in.UserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "User Management", description = "APIs for user authentication and registration")
public class UserController {

    private final UserUseCase userUseCase;

    @GetMapping("/login")
    @Operation(summary = "User login", description = "Authenticate user with email and password")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login successful"),
        @ApiResponse(responseCode = "401", description = "Invalid credentials"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<UserDto> login(
            @Parameter(description = "User email", example = "john.doe@example.com")
            @RequestParam String email,
            @Parameter(description = "User password", example = "password123")
            @RequestParam String password) {
        
        log.info("Login attempt for user: {}", email);
        
        try {
            Optional<UserDto> user = userUseCase.login(email, password);
            if (user.isPresent()) {
                log.info("Login successful for user: {}", email);
                return ResponseEntity.ok(user.get());
            } else {
                log.warn("Login failed for user: {}", email);
                return ResponseEntity.status(401).build();
            }
        } catch (Exception error) {
            log.error("Error during login for user {}: {}", email, error.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/register")
    @Operation(summary = "User registration", description = "Register a new user with email and password")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Registration successful"),
        @ApiResponse(responseCode = "400", description = "User already exists"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<UserDto> register(
            @Parameter(description = "User email", example = "john.doe@example.com")
            @RequestParam String email,
            @Parameter(description = "User password", example = "password123")
            @RequestParam String password) {
        
        log.info("Registration attempt for user: {}", email);
        
        try {
            UserDto userDto = UserDto.builder()
                    .email(email)
                    .password(password)
                    .build();
            
            UserDto registeredUser = userUseCase.register(userDto);
            log.info("Registration successful for user: {}", email);
            return ResponseEntity.ok(registeredUser);
        } catch (RuntimeException error) {
            if (error.getMessage().contains("already exists")) {
                log.warn("Registration failed - user already exists: {}", email);
                return ResponseEntity.status(400).build();
            }
            log.error("Error during registration for user {}: {}", email, error.getMessage());
            return ResponseEntity.status(500).build();
        } catch (Exception error) {
            log.error("Error during registration for user {}: {}", email, error.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}