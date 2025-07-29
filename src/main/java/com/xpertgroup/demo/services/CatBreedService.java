package com.xpertgroup.demo.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.xpertgroup.demo.dtos.CatBreedDto;
import com.xpertgroup.demo.ports.in.CatBreedUseCase;
import com.xpertgroup.demo.ports.out.CatBreedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CatBreedService implements CatBreedUseCase {

    private final CatBreedRepository catBreedRepository;

    @Override
    public List<CatBreedDto> getAllBreeds() {
        log.info("Fetching all cat breeds");
        try {
            List<CatBreedDto> breeds = catBreedRepository.getAllBreeds();
            log.info("Successfully fetched {} cat breeds", breeds.size());
            return breeds;
        } catch (Exception error) {
            log.error("Error fetching all cat breeds: {}", error.getMessage());
            throw error;
        }
    }

    @Override
    public Optional<CatBreedDto> getBreedById(String breedId) {
        log.info("Fetching cat breed with id: {}", breedId);
        try {
            Optional<CatBreedDto> breed = catBreedRepository.getBreedById(breedId);
            breed.ifPresent(b -> log.info("Successfully fetched cat breed: {}", b.getName()));
            return breed;
        } catch (Exception error) {
            log.error("Error fetching cat breed with id {}: {}", breedId, error.getMessage());
            throw error;
        }
    }

    @Override
    public List<CatBreedDto> searchBreeds(String query) {
        log.info("Searching cat breeds with query: {}", query);
        try {
            List<CatBreedDto> breeds = catBreedRepository.searchBreeds(query);
            log.info("Successfully searched cat breeds with query: {}, found {} results", query, breeds.size());
            return breeds;
        } catch (Exception error) {
            log.error("Error searching cat breeds with query {}: {}", query, error.getMessage());
            throw error;
        }
    }
}