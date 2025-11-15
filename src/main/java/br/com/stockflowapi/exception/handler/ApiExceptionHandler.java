package br.com.stockflowapi.exception.handler;

import br.com.stockflowapi.exception.custom.EntityNotFoundException;
import br.com.stockflowapi.exception.dto.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                HttpServletRequest request,
                                                                BindingResult bindingResult){
        return ResponseEntity
                .status(UNPROCESSABLE_ENTITY)
                .contentType(APPLICATION_JSON)
                .body(ErrorMessage.builder()
                        .timestamp(LocalDateTime.now())
                        .code(UNPROCESSABLE_ENTITY.value())
                        .status(UNPROCESSABLE_ENTITY.name())
                        .method(request.getMethod())
                        .requestURI(request.getRequestURI())
                        .errors(new HashMap<>())
                        .message(exception.getMessage())
                        .build().addErrors(bindingResult));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(RuntimeException exception,
                                                                HttpServletRequest request){
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
