package com.xpertgroup.demo.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta de autenticación con JWT")
public class LoginResponseDto {
    @Schema(description = "Token JWT para autenticación", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;
    
    @Schema(description = "Tipo de token", example = "Bearer")
    private String tokenType;
    
    @Schema(description = "Duración del token en segundos", example = "7200")
    private Long expiresIn;
    
    @Schema(description = "Información del usuario autenticado")
    private UserDto user;
}