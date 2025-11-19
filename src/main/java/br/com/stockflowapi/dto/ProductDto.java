package br.com.stockflowapi.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record ProductDto(
        @NotNull @Schema(example = "10000") Long code,
        @NotBlank @Schema(example = "SSD 1TB") String name,
        @NotBlank @Schema(example = "Product description") String description,
        @DecimalMin("0.01") @Schema(example = "499.99") BigDecimal price,
        @ArraySchema(schema = @Schema(example = "https://storage.com/image1.jpg")) List<String> images,
        @NotNull @Schema(example = "1") Long categoryId
) {
}
