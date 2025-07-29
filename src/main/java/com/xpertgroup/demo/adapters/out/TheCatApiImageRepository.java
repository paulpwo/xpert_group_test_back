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
import com.xpertgroup.demo.dtos.CatImageDto;
import com.xpertgroup.demo.ports.out.CatImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TheCatApiImageRepository implements CatImageRepository {

    private final RestTemplate restTemplate;

    @Value("${thecatapi.base-url}")
    private String baseUrl;

    @Value("${thecatapi.api-key}")
    private String apiKey;

    @Override
    public List<CatImageDto> getImagesByBreed(String breedId) {
        try {
            String url = baseUrl + "/images/search?breed_ids=" + breedId + "&limit=10";
            
            HttpHeaders headers = new HttpHeaders();
            headers.set("x-api-key", apiKey);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<CatImageDto[]> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, CatImageDto[].class);

            List<CatImageDto> images = Arrays.asList(response.getBody());
            return images;
        } catch (Exception error) {
            log.error("Failed to fetch images for breed {} from The Cat API: {}", breedId, error.getMessage());
            throw new RuntimeException("Error fetching images from The Cat API", error);
        }
    }

    @Override
    public Optional<CatImageDto> getImageById(String imageId) {
        try {
            String url = baseUrl + "/images/" + imageId;
            
            HttpHeaders headers = new HttpHeaders();
            headers.set("x-api-key", apiKey);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<CatImageDto> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, CatImageDto.class);

            CatImageDto image = response.getBody();
            if (image != null) {
                return Optional.of(image);
            } else {
                log.warn("Image {} not found in The Cat API", imageId);
                return Optional.empty();
            }
        } catch (Exception error) {
            log.error("Failed to fetch image {} from The Cat API: {}", imageId, error.getMessage());
            throw new RuntimeException("Error fetching image from The Cat API", error);
        }
    }

    @Override
    public List<CatImageDto> searchImages(String query) {
        try {
            String url = baseUrl + "/images/search?q=" + query + "&limit=10";
            
            HttpHeaders headers = new HttpHeaders();
            headers.set("x-api-key", apiKey);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<CatImageDto[]> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, CatImageDto[].class);

            List<CatImageDto> images = Arrays.asList(response.getBody());
            return images;
        } catch (Exception error) {
            log.error("Failed to search images with query '{}' from The Cat API: {}", query, error.getMessage());
            throw new RuntimeException("Error searching images from The Cat API", error);
        }
    }
}