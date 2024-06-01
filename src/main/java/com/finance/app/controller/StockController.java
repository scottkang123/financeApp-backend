package com.finance.app.controller;

import com.finance.app.dto.StockDTO;
import com.finance.app.service.StockService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("stock")
@RequiredArgsConstructor
@Tag(name = "Stock")
public class StockController {

    private static final Logger logger = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private StockService stockService;


    @PostMapping("/fetchStockData")
    public ResponseEntity<Void> fetchStockData() {
        try {
            logger.info("Fetching S&P 500 symbols");

            // Fetch S&P 500 symbols
            List<String> sp500Symbols = stockService.fetchSp500Symbols();

            logger.info("Sp500 symbols {}", sp500Symbols);
            logger.info("Fetching and saving data for each symbol");

            int count = 0;
            // Fetch and save data for each symbol
            for (String symbol : sp500Symbols) {
                if(count > 3)
                    break;
                if (stockService.isStockDataMissing(symbol)) {
                    StockDTO stockDTO = stockService.fetchStockDataFromAlphaVantage(symbol);
                    stockService.saveStockData(stockDTO);
                }
                count++;
            }
            logger.info("Data fetching and saving completed successfully");

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("An error occurred while fetching or saving stock data", e);

            return ResponseEntity.status(500).build();
        }
    }





}