package br.com.stockflowapi.exception.custom;

public class UploadSizeExceededException extends RuntimeException {
    public UploadSizeExceededException(String message) {
        super(message);
    }
}
