package com.microservice.stock_ms.exception;

import com.microservice.stock_ms.controller.advice.GlobalExceptionHandler;
import com.microservice.stock_ms.model.dto.ErrorDetailDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class GlobalExceptionHandlerTest {


    private GlobalExceptionHandler handler;

    @BeforeEach
    void init(){
        handler = new GlobalExceptionHandler();
    }

    @Test
    void handleInvalidDataExceptionTest() {
        InvalidDataException ex = new InvalidDataException("Quantity of product cannot be 0");
        ResponseEntity<ErrorDetailDto> response = handler.handleInvalidDataException(ex);

        assertEquals("Quantity of product cannot be 0", response.getBody().getMessage());
        assertNotNull(response.getBody().getDateTime());
    }

    @Test
    void handleProductNotFoundExceptionTest() {
        ResponseEntity<Void> response = handler.handleProductNotFoundException();
        assertEquals(204, response.getStatusCode().value()); // 204 No Content
    }
}
