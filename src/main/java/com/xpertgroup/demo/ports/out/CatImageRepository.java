package com.xpertgroup.demo.ports.out;

import java.util.List;
import java.util.Optional;
import com.xpertgroup.demo.dtos.CatImageDto;

public interface CatImageRepository {
    List<CatImageDto> getImagesByBreed(String breedId);
    Optional<CatImageDto> getImageById(String imageId);
    List<CatImageDto> searchImages(String query);
}