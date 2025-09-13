package com.microservice.stock_ms.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ErrorDetailDto {

    private String message;
    private LocalDateTime dateTime;
}
