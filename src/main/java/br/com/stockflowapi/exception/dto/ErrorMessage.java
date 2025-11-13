package br.com.stockflowapi.exception.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorMessage(
    LocalDateTime timestamp,
    Integer code,
    String status,
    String message,
    String method,
    String requestURI
) { }
