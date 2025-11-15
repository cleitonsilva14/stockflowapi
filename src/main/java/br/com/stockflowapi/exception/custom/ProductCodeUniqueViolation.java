package br.com.stockflowapi.exception.custom;

public class ProductCodeUniqueViolation extends RuntimeException {
    public ProductCodeUniqueViolation(String message) {
        super(message);
    }
}
