package br.com.stockflowapi.exception.handler;

import br.com.stockflowapi.exception.custom.EntityNotFoundException;
import br.com.stockflowapi.exception.dto.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(RuntimeException exception, HttpServletRequest request){
        return ResponseEntity
                .status(NOT_FOUND)
                .contentType(APPLICATION_JSON)
                .body(ErrorMessage.builder()
                        .timestamp(LocalDateTime.now())
                        .code(NOT_FOUND.value())
                        .status(NOT_FOUND.name())
                        .method(request.getMethod())
                        .requestURI(request.getRequestURI())
                        .message(exception.getMessage())
                        .build());
    }


}
