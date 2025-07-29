package com.xpertgroup.demo.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.xpertgroup.demo.dtos.CatBreedDto;
import com.xpertgroup.demo.ports.in.CatBreedUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/breeds")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Cat Breeds", description = "Endpoints para gestionar razas de gatos")
public class CatBreedController {

    private final CatBreedUseCase catBreedUseCase;

    @GetMapping
    @Operation(
            summary = "Obtener todas las razas de gatos",
            description = "Retorna una lista completa de todas las razas de gatos disponibles"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de razas obtenida exitosamente",
                    content = @Content(schema = @Schema(implementation = CatBreedDto.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<CatBreedDto>> getAllBreeds() {
        log.info("GET /breeds - Fetching all cat breeds");
        List<CatBreedDto> breeds = catBreedUseCase.getAllBreeds();
        return ResponseEntity.ok(breeds);
    }

    @GetMapping("/{breedId}")
    @Operation(
            summary = "Obtener raza por ID",
            description = "Retorna información detallada de una raza específica por su ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Raza encontrada exitosamente",
                    content = @Content(schema = @Schema(implementation = CatBreedDto.class))),
            @ApiResponse(responseCode = "404", description = "Raza no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<CatBreedDto> getBreedById(
            @Parameter(description = "ID de la raza de gato", example = "abys")
            @PathVariable String breedId) {
        log.info("GET /breeds/{} - Fetching cat breed by id", breedId);
        Optional<CatBreedDto> breed = catBreedUseCase.getBreedById(breedId);
        return breed.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    @Operation(
            summary = "Buscar razas de gatos",
            description = "Busca razas de gatos que coincidan con el término de búsqueda"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente",
                    content = @Content(schema = @Schema(implementation = CatBreedDto.class))),
            @ApiResponse(responseCode = "400", description = "Parámetro de búsqueda requerido"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<CatBreedDto>> searchBreeds(
            @Parameter(description = "Término de búsqueda", example = "abyssinian")
            @RequestParam String q) {
        log.info("GET /breeds/search?q={} - Searching cat breeds", q);
        List<CatBreedDto> breeds = catBreedUseCase.searchBreeds(q);
        return ResponseEntity.ok(breeds);
    }
}