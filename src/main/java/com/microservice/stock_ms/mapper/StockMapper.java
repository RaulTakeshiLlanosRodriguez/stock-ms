package com.microservice.stock_ms.mapper;

import com.microservice.stock_ms.model.dto.FindByProductIdDto;
import com.microservice.stock_ms.model.dto.SaveStockRequestDto;
import com.microservice.stock_ms.model.dto.SaveStockResponseDto;
import com.microservice.stock_ms.model.entity.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StockMapper {

    StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

    SaveStockResponseDto toResponseDto(Stock entity);

    List<SaveStockResponseDto> toResponseDtoList(List<Stock> entityList);
    Stock toEntity(SaveStockRequestDto dto);
    List<Stock> toEntityList(List<SaveStockRequestDto> dtoList);

    default FindByProductIdDto toFindByProductIdDto(Integer productId, Integer total) {
        return FindByProductIdDto.builder()
                .productId(productId)
                .total(total != null ? total : 0)
                .build();
    }
}
