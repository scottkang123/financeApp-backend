package com.finance.app.scheduler;

import com.finance.app.dto.StockDTO;
import com.finance.app.model.Stock;
import com.finance.app.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

@Component
public class StockScheduler {

    @Autowired
    private StockService stockService;

    @Scheduled(cron = "0 0 0 * * ?") // This cron expression means midnight every day
    public void updateStockData() {
        try {
            // Fetch S&P 500 symbols
            List<String> sp500Symbols = stockService.fetchSp500Symbols();

            // Fetch and save data for each symbol
            for (String symbol : sp500Symbols) {
                StockDTO stockDTO = stockService.fetchStockDataFromAlphaVantage(symbol);
                stockService.saveStockData(stockDTO);
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
            // Handle the exception, log it or perform any necessary actions
        }
    }
}