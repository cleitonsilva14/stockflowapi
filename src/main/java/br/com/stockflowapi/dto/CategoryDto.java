package br.com.stockflowapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CategoryDto(
        Long id,
        @NotBlank String name
) {
}
