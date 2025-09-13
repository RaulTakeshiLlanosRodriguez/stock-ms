package com.microservice.stock_ms.controller;

import com.microservice.stock_ms.controller.advice.GlobalExceptionHandler;
import com.microservice.stock_ms.mapper.StockMapper;
import com.microservice.stock_ms.model.dto.FindByProductIdDto;
import com.microservice.stock_ms.model.dto.SaveStockRequestDto;
import com.microservice.stock_ms.model.dto.SaveStockResponseDto;
import com.microservice.stock_ms.model.entity.Stock;
import com.microservice.stock_ms.service.StockServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class StockControllerTest {


    private MockMvc mockMvc;

    @Mock
    private StockServiceImpl stockService;

    @InjectMocks
    private StockController stockController;

    private SaveStockRequestDto requestDto;

    private SaveStockResponseDto responseDto;

    @MockitoBean
    private StockMapper stockMapper;

    @BeforeEach
    void setUp() {
        requestDto = new SaveStockRequestDto();
        responseDto = new SaveStockResponseDto();
        mockMvc = MockMvcBuilders.standaloneSetup(stockController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void createStockTest() throws Exception {

        requestDto.setProductId(1);
        requestDto.setWareHouseId(1);
        requestDto.setQuantity(10);


        responseDto.setProductId(1);
        responseDto.setQuantity(10);
        responseDto.setWareHouseId(1);

        when(stockService.save(anyList())).thenReturn(Collections.singletonList(new Stock()));
        when(stockMapper.toResponseDtoList(anyList())).thenReturn(Collections.singletonList(responseDto));

        String requestJson = "[{\"productId\":1, \"wareHouseId\":1, \"quantity\":10}]";

        mockMvc.perform(post("/stock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[0].productId").value(1))
                .andExpect(jsonPath("$[0].quantity").value(10))
                .andExpect(jsonPath("$[0].wareHouseId").value(1));
    }

    @Test
    void findByProductIdTest() throws Exception {

        Integer productId = 1;
        Integer total = 10;
        FindByProductIdDto dto = FindByProductIdDto.builder()

                .productId(productId)
                .total(total)
                .build();

        when(stockService.stockByProductId(productId)).thenReturn(total);
        when(stockMapper.toFindByProductIdDto(productId, total)).thenReturn(dto);

        mockMvc.perform(get("/stock/{productId}", productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(productId))
                .andExpect(jsonPath("$.total").value(total));
    }
}
