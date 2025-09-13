package com.microservice.stock_ms.controller;

import com.microservice.stock_ms.mapper.StockMapper;
import com.microservice.stock_ms.model.dto.FindByProductIdDto;
import com.microservice.stock_ms.model.dto.SaveStockRequestDto;
import com.microservice.stock_ms.model.dto.SaveStockResponseDto;
import com.microservice.stock_ms.service.StockService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService service;

    @PostMapping
    public ResponseEntity<List<SaveStockResponseDto>> create(@Valid @RequestBody List<SaveStockRequestDto> saveStockRequestDtos){
        return ResponseEntity.status(HttpStatus.CREATED).body(StockMapper.INSTANCE.toResponseDtoList(service.save(StockMapper.INSTANCE.toEntityList(saveStockRequestDtos))));
    }


    @GetMapping("/{productId}")
    public ResponseEntity<FindByProductIdDto> findByProductId(@PathVariable Integer productId){
        return ResponseEntity.ok(StockMapper.INSTANCE.toFindByProductIdDto(productId, service.stockByProductId(productId)));
    }
}
