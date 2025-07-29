package com.xpertgroup.demo.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.xpertgroup.demo.dtos.CatImageDto;
import com.xpertgroup.demo.ports.in.CatImageUseCase;
import com.xpertgroup.demo.ports.out.CatImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CatImageService implements CatImageUseCase {

    private final CatImageRepository catImageRepository;

    @Override
    public List<CatImageDto> getImagesByBreed(String breedId) {
        log.info("Fetching images for breed: {}", breedId);
        try {
            List<CatImageDto> images = catImageRepository.getImagesByBreed(breedId);
            log.info("Successfully fetched {} images for breed: {}", images.size(), breedId);
            return images;
        } catch (Exception error) {
            log.error("Error fetching images for breed {}: {}", breedId, error.getMessage());
            throw error;
        }
    }

}