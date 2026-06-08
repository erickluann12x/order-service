package com.example.order_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handlerOrderNotFound(OrderNotFoundException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
    @ExceptionHandler(PaymentServiceUnavailableException.class)
    public ResponseEntity<ErrorResponse> handlePaymentUnavailable(PaymentServiceUnavailableException ex){

        ErrorResponse error = new ErrorResponse(HttpStatus.SERVICE_UNAVAILABLE.value(),
                ex.getMessage(),
                java.time.LocalDateTime.now());
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
    }
}
