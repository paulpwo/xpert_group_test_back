package com.xpertgroup.demo.ports.in;

import java.util.List;
import com.xpertgroup.demo.dtos.CatImageDto;

public interface CatImageUseCase {
    List<CatImageDto> getImagesByBreed(String breedId);
}