package com.finance.app.controller;

import com.finance.app.dto.StockDTO;
import com.finance.app.service.StockService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stock")
@RequiredArgsConstructor
@Tag(name = "Stock")
public class StockController {

    //private final StockService service;
    @Autowired
    private StockService stockService;

    @PostMapping("/fetch-and-save-stocks")
    public List<StockDTO> fetchAndSaveStocks() {
        List<StockDTO> stockDTOs = stockService.fetchStockData();
        stockService.saveStockData(stockDTOs);
        return stockDTOs;
    }



}