package dev.dwidi.transactionservice.exception;

import dev.dwidi.transactionservice.dto.PublicResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<PublicResponseDTO<Void>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        PublicResponseDTO<Void> response = new PublicResponseDTO<>(404, ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<PublicResponseDTO<Void>> handleException(Exception ex) {
        PublicResponseDTO<Void> response = new PublicResponseDTO<>(500, "Internal Server Error: " + ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
