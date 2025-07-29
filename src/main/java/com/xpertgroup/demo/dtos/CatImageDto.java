package com.xpertgroup.demo.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Data Transfer Object para la entidad CatBreed
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Información de una imagen de gato")
public class CatImageDto {
    
    @Schema(description = "ID único de la imagen", example = "0XYvRd7oD")
    private String id;
    
    @Schema(description = "URL de la imagen", example = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg")
    private String url;
    
    @Schema(description = "Ancho de la imagen en píxeles", example = "1204")
    private Integer width;
    
    @Schema(description = "Alto de la imagen en píxeles", example = "1445")
    private Integer height;
    
    @Schema(description = "ID de la raza asociada", example = "abys")
    private String breedId;
    
    @Schema(description = "Nombre de la raza asociada", example = "Abyssinian")
    private String breedName;
}