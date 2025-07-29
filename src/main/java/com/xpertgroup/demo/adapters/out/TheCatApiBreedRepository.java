package com.xpertgroup.demo.adapters.out;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import com.xpertgroup.demo.dtos.CatBreedDto;
import com.xpertgroup.demo.ports.out.CatBreedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TheCatApiBreedRepository implements CatBreedRepository {

    private final RestTemplate restTemplate;

    @Value("${thecatapi.base-url}")
    private String baseUrl;

    @Value("${thecatapi.api-key}")
    private String apiKey;

    @Override
    public List<CatBreedDto> getAllBreeds() {
        try {
            String url = baseUrl + "/breeds";
            
            HttpHeaders headers = new HttpHeaders();
            headers.set("x-api-key", apiKey);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<CatBreedDto[]> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, CatBreedDto[].class);

            List<CatBreedDto> breeds = Arrays.asList(response.getBody());
            return breeds;
        } catch (Exception error) {
            log.error("Failed to fetch breeds from The Cat API: {}", error.getMessage());
            throw new RuntimeException("Error fetching breeds from The Cat API", error);
        }
    }

    @Override
    public Optional<CatBreedDto> getBreedById(String breedId) {
        try {
            log.info("Fetching breed {} from The Cat API", breedId);
            String url = baseUrl + "/breeds/" + breedId;
            
            HttpHeaders headers = new HttpHeaders();
            headers.set("x-api-key", apiKey);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<CatBreedDto> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, CatBreedDto.class);

            CatBreedDto breed = response.getBody();
            if (breed != null) {
                return Optional.of(breed);
            } else {
                log.warn("Breed {} not found in The Cat API", breedId);
                return Optional.empty();
            }
        } catch (Exception error) {
            log.error("Failed to fetch breed {} from The Cat API: {}", breedId, error.getMessage());
            throw new RuntimeException("Error fetching breed from The Cat API", error);
        }
    }

    @Override
    public List<CatBreedDto> searchBreeds(String query) {
        try {
            String url = baseUrl + "/breeds/search?q=" + query;
            
            HttpHeaders headers = new HttpHeaders();
            headers.set("x-api-key", apiKey);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<CatBreedDto[]> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, CatBreedDto[].class);

            List<CatBreedDto> breeds = Arrays.asList(response.getBody());
            return breeds;
        } catch (Exception error) {
            log.error("Failed to search breeds with query '{}' from The Cat API: {}", query, error.getMessage());
            throw new RuntimeException("Error searching breeds from The Cat API", error);
        }
    }
}