package br.com.stockflowapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CategoryRequestDto(
        @NotBlank @Schema(example = "HARDWARE") String name
) {
}
