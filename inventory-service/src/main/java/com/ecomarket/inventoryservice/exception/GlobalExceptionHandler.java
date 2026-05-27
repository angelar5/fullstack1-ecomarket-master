package com.ecomarket.inventoryservice.exception;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(404).body(ErrorResponse.builder().timestamp(LocalDateTime.now()).status(404).error("Not Found").message(ex.getMessage()).build());
    }
}