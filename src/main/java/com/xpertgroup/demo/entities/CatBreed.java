package com.xpertgroup.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cat_breeds")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatBreed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String breedId;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(length = 500)
    private String temperament;

    private String origin;

    @Column(name = "life_span")
    private String lifeSpan;

    private Integer intelligence;

    @Column(name = "wikipedia_url")
    private String wikipediaUrl;

    @Column(name = "image_url")
    private String imageUrl;

    @Embedded
    private Weight weight;

    @Column(name = "vetstreet_url")
    private String vetstreetUrl;

    @Column(name = "country_codes")
    private String countryCodes;

    @Column(name = "country_code")
    private String countryCode;

    private Integer indoor;

    @Column(name = "alt_names")
    private String altNames;

    private Integer adaptability;

    @Column(name = "affection_level")
    private Integer affectionLevel;

    @Column(name = "child_friendly")
    private Integer childFriendly;

    @Column(name = "dog_friendly")
    private Integer dogFriendly;

    @Column(name = "energy_level")
    private Integer energyLevel;

    private Integer grooming;

    @Column(name = "health_issues")
    private Integer healthIssues;

    @Column(name = "shedding_level")
    private Integer sheddingLevel;

    @Column(name = "social_needs")
    private Integer socialNeeds;

    @Column(name = "stranger_friendly")
    private Integer strangerFriendly;

    private Integer vocalisation;

    private Integer experimental;

    private Integer hairless;

    @Column(name = "is_natural")
    private Integer natural;

    private Integer rare;

    private Integer rex;

    @Column(name = "suppressed_tail")
    private Integer suppressedTail;

    @Column(name = "short_legs")
    private Integer shortLegs;

    private Integer hypoallergenic;

    @Column(name = "reference_image_id")
    private String referenceImageId;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Weight {
        private String imperial;
        private String metric;
    }
}
