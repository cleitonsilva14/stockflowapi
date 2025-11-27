package br.com.stockflowapi.dto;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record ProductRequestDto(
        Long code,
        String name,
        String description,
        BigDecimal price,
        List<MultipartFile> images,
        Long categoryId
) {
}
