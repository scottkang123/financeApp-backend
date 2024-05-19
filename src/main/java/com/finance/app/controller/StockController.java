package com.finance.app.controller;

import com.finance.app.dto.StockDTO;
import com.finance.app.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/fetch-and-save-stocks")
    public List<StockDTO> fetchAndSaveStocks() {
        List<StockDTO> stockDTOs = stockService.fetchStockData();
        stockService.saveStockData(stockDTOs);
        return stockDTOs;
    }


}