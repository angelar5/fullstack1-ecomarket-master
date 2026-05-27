package com.ecomarket.userservice.exception;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;
@ControllerAdvice @Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex, HttpServletRequest req) {
        log.warn("User no encontrado: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
            .timestamp(LocalDateTime.now()).status(404).error("Not Found").message(ex.getMessage()).path(req.getRequestURI()).build());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        Map<String, String> details = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(err -> details.put(((FieldError)err).getField(), err.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder()
            .timestamp(LocalDateTime.now()).status(400).error("Validation Failed").message("Datos invalidos").path(req.getRequestURI()).details(details).build());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex, HttpServletRequest req) {
        log.error("Error no controlado", ex);
        return ResponseEntity.status(500).body(ErrorResponse.builder()
            .timestamp(LocalDateTime.now()).status(500).error("Server Error").message("Error interno").path(req.getRequestURI()).build());
    }
}