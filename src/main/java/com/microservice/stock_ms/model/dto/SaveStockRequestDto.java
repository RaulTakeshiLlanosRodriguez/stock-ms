package com.microservice.stock_ms.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveStockRequestDto {

    private Integer productId;
    private Integer wareHouseId;
    private Integer quantity;
}
