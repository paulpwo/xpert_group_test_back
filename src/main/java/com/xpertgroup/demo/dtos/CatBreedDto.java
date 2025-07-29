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
@Schema(description = "Información de una raza de gato")
public class CatBreedDto {
    
    @Schema(description = "ID único de la raza", example = "abys")
    private String id;
    
    @Schema(description = "Nombre de la raza", example = "Abyssinian")
    private String name;
    
    @Schema(description = "Descripción de la raza", example = "The Abyssinian is as close as you can get to the lions of Africa.")
    private String description;
    
    @Schema(description = "Temperamento de la raza", example = "Active, Energetic, Independent, Intelligent, Gentle")
    private String temperament;
    
    @Schema(description = "Origen de la raza", example = "Egypt")
    private String origin;
    
    @Schema(description = "Esperanza de vida", example = "14 - 15")
    private String lifeSpan;
    
    @Schema(description = "Nivel de inteligencia (1-5)", example = "5")
    private Integer intelligence;
    
    @Schema(description = "URL de Wikipedia", example = "https://en.wikipedia.org/wiki/Abyssinian_(cat)")
    private String wikipediaUrl;
    
    @Schema(description = "URL de la imagen de referencia")
    private String imageUrl;
    
    @Schema(description = "Información de peso")
    private WeightDto weight;
    
    @Schema(description = "URL de Vetstreet")
    private String vetstreetUrl;
    
    @Schema(description = "Códigos de país", example = "EG")
    private String countryCodes;
    
    @Schema(description = "Código de país", example = "EG")
    private String countryCode;
    
    @Schema(description = "Indicador si es gato de interior (0-1)", example = "0")
    private Integer indoor;
    
    @Schema(description = "Nombres alternativos")
    private String altNames;
    
    @Schema(description = "Nivel de adaptabilidad (1-5)", example = "5")
    private Integer adaptability;
    
    @Schema(description = "Nivel de afecto (1-5)", example = "5")
    private Integer affectionLevel;
    
    @Schema(description = "Amigable con niños (1-5)", example = "3")
    private Integer childFriendly;
    
    @Schema(description = "Amigable con perros (1-5)", example = "3")
    private Integer dogFriendly;
    
    @Schema(description = "Nivel de energía (1-5)", example = "5")
    private Integer energyLevel;
    
    @Schema(description = "Nivel de aseo (1-5)", example = "1")
    private Integer grooming;
    
    @Schema(description = "Problemas de salud (1-5)", example = "2")
    private Integer healthIssues;
    
    @Schema(description = "Nivel de muda (1-5)", example = "2")
    private Integer sheddingLevel;
    
    @Schema(description = "Necesidades sociales (1-5)", example = "5")
    private Integer socialNeeds;
    
    @Schema(description = "Amigable con extraños (1-5)", example = "5")
    private Integer strangerFriendly;
    
    @Schema(description = "Nivel de vocalización (1-5)", example = "1")
    private Integer vocalisation;
    
    @Schema(description = "Indicador experimental (0-1)", example = "0")
    private Integer experimental;
    
    @Schema(description = "Indicador sin pelo (0-1)", example = "0")
    private Integer hairless;
    
    @Schema(description = "Indicador natural (0-1)", example = "0")
    private Integer natural;
    
    @Schema(description = "Indicador raro (0-1)", example = "0")
    private Integer rare;
    
    @Schema(description = "Indicador rex (0-1)", example = "0")
    private Integer rex;
    
    @Schema(description = "Indicador cola suprimida (0-1)", example = "0")
    private Integer suppressedTail;
    
    @Schema(description = "Indicador patas cortas (0-1)", example = "0")
    private Integer shortLegs;
    
    @Schema(description = "Indicador hipoalergénico (0-1)", example = "0")
    private Integer hypoallergenic;
    
    @Schema(description = "ID de la imagen de referencia")
    private String referenceImageId;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Información de peso de la raza")
    public static class WeightDto {
        
        @Schema(description = "Peso en sistema imperial", example = "7-10")
        private String imperial;
        
        @Schema(description = "Peso en sistema métrico", example = "3-5")
        private String metric;
    }
}