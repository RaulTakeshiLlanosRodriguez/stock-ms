package com.microservice.stock_ms.service;

import com.microservice.stock_ms.exception.InvalidDataException;
import com.microservice.stock_ms.exception.ProductNotFoundException;
import com.microservice.stock_ms.model.entity.Stock;
import com.microservice.stock_ms.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService{

    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<Stock> save(List<Stock> stocks) {

        stocks.forEach(stock ->{
            if(stock.getQuantity() == 0){
                throw new InvalidDataException("Quantity of product cannot be 0");
            }
        });

        return (List<Stock>) stockRepository.saveAll(stocks);
    }

    @Override
    public Integer stockByProductId(Integer productId) {

        Integer total = stockRepository.stockByProductId(productId);
        if(total == 0){
            throw new ProductNotFoundException();
        }
        return stockRepository.stockByProductId(productId);
    }
}
