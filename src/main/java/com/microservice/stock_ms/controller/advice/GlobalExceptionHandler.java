package com.microservice.stock_ms.controller.advice;

import com.microservice.stock_ms.exception.InvalidDataException;
import com.microservice.stock_ms.exception.ProductNotFoundException;
import com.microservice.stock_ms.model.dto.ErrorDetailDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Void> handleProductNotFoundException(){
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorDetailDto> handleInvalidDataException(InvalidDataException ex){
        return ResponseEntity.ok(ErrorDetailDto.builder()
                        .message(ex.getMessage())
                        .dateTime(LocalDateTime.now())
                .build());
    }
}
