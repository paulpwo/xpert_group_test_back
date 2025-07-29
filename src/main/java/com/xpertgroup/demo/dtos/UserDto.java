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
@Schema(description = "Información de un usuario")
public class UserDto {
    @Schema(description = "Email del usuario", example = "paulpwo@gmail.com")
    private String email;
    @Schema(description = "Contraseña del usuario", example = "password123")
    private String password;
}
