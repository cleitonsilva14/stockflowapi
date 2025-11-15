package br.com.stockflowapi.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record ProductDto(
        @NotNull Long code,
        @NotBlank String name,
        @NotBlank String description,
        @DecimalMin("0.01") BigDecimal price,
        List<String> images
) {
}
