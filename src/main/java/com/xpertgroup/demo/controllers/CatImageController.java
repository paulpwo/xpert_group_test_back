package com.xpertgroup.demo.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.xpertgroup.demo.dtos.CatImageDto;
import com.xpertgroup.demo.ports.in.CatImageUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Cat Images", description = "Endpoints para gestionar imágenes de gatos")
@SecurityRequirement(name = "bearerAuth")
public class CatImageController {

    private final CatImageUseCase catImageUseCase;

    @GetMapping("/breed/{breedId}")
    @Operation(
            summary = "Obtener imágenes por raza",
            description = "Retorna imágenes de gatos de una raza específica"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Imágenes obtenidas exitosamente",
                    content = @Content(schema = @Schema(implementation = CatImageDto.class))),
            @ApiResponse(responseCode = "401", description = "No autorizado - Token JWT requerido"),
            @ApiResponse(responseCode = "404", description = "Raza no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<CatImageDto>> getImagesByBreed(
            @Parameter(description = "ID de la raza de gato", example = "abys")
            @PathVariable String breedId) {
        log.info("GET /images/breed/{} - Fetching images for breed", breedId);
        List<CatImageDto> images = catImageUseCase.getImagesByBreed(breedId);
        return ResponseEntity.ok(images);
    }
}