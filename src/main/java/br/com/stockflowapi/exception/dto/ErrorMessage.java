package br.com.stockflowapi.exception.dto;

import lombok.Builder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Builder(toBuilder = true)
public record ErrorMessage(
        LocalDateTime timestamp,
        Integer code,
        String status,
        String message,
        String method,
        String requestURI,
        Map<String, String> errors
) {

    public ErrorMessage addErrors(BindingResult bindingResult) {
        Map<String, String> newErrors = new HashMap<>();
        if (this.errors != null) {
            newErrors.putAll(this.errors);
        }

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            newErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return this.toBuilder()
                .errors(newErrors)
                .build();
    }

}
