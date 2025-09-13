package com.microservice.stock_ms.service;

import com.microservice.stock_ms.exception.InvalidDataException;
import com.microservice.stock_ms.exception.ProductNotFoundException;
import com.microservice.stock_ms.model.entity.Stock;
import com.microservice.stock_ms.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl service;

    private Stock stock;

    @BeforeEach
    void init(){
        stock = new Stock();
    }

    @Test
    void saveStockTest() {
        stock.setProductId(1);
        stock.setWareHouseId(1);
        stock.setQuantity(10);

        when(stockRepository.saveAll(anyList())).thenReturn(List.of(stock));
        List<Stock> stocksSaved = service.save(List.of(stock));

        assertEquals(1, stocksSaved.size());
        assertEquals(10, stocksSaved.get(0).getQuantity());
    }

    @Test
    void saveStockInvalidQuantityTest() {
        stock.setProductId(1);
        stock.setWareHouseId(1);
        stock.setQuantity(0);

        assertThrows(InvalidDataException.class, () -> {
            service.save(List.of(stock));
        });
    }


    @Test
    void stockByProductIdTest() {

        when(stockRepository.stockByProductId(1)).thenReturn(100);

        Integer total = service.stockByProductId(1);

        assertEquals(100, total);
    }

    @Test
    void stockByProductIdNotFoundTest() {
        when(stockRepository.stockByProductId(1)).thenReturn(0);

        assertThrows(ProductNotFoundException.class, () -> {
            service.stockByProductId(1);
        });
    }
}
