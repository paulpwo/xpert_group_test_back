package com.xpertgroup.demo.ports.out;

import java.util.List;
import java.util.Optional;
import com.xpertgroup.demo.dtos.CatBreedDto;

public interface CatBreedRepository {
    List<CatBreedDto> getAllBreeds();
    Optional<CatBreedDto> getBreedById(String breedId);
    List<CatBreedDto> searchBreeds(String query);
}