package com.microservice.stock_ms.service;

import com.microservice.stock_ms.model.entity.Stock;

import java.util.List;

public interface StockService {

    List<Stock> save(List<Stock> stocks);
    Integer stockByProductId(Integer productId);
}
