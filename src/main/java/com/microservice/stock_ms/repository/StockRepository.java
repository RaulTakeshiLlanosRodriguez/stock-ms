package com.microservice.stock_ms.repository;

import com.microservice.stock_ms.model.entity.Stock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock, Integer> {

    @Query("SELECT COALESCE(SUM(s.quantity), 0) FROM Stock s WHERE s.productId = ?1")
    Integer stockByProductId(Integer productId);
}
