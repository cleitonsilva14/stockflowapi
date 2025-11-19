package br.com.stockflowapi.dto;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponseDto(
        Long code,
        String name,
        String description,
        BigDecimal price,
        List<String> images,
        CategoryDto category

) {
}
