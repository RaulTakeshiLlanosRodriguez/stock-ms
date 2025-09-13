package com.microservice.stock_ms.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FindByProductIdDto {

    private Integer productId;
    private Integer total;
}
