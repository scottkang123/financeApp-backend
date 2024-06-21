package com.finance.app.scheduler;

import com.finance.app.controller.StockController;
import com.finance.app.dto.StockDTO;
import com.finance.app.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class StockScheduler {

    private static final Logger logger = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private StockService stockService;

    @Scheduled(cron = "0 0 0 * * ?") // This cron expression means midnight every day
    public void updateStockData() {
        try {
            // Fetch S&P 500 symbols
            List<String> sp500Symbols = stockService.fetchSp500Symbols();

            // Fetch and save data for each symbol
            for (String symbol : sp500Symbols) {
                String modifiedSymbol = symbol;
                // Modify symbol if necessary
                if (symbol.equalsIgnoreCase("BRK.B")) {
                    modifiedSymbol = "BRK-B";
                }
                if (stockService.isStockDataMissing(modifiedSymbol)) {
                    StockDTO stockDTO = stockService.fetchStockDataFromAlphaVantage(modifiedSymbol);
                    if (stockDTO.getName() != null) {
                        stockService.saveStockData(stockDTO);
                    } else {
                        logger.info("Skipping symbol {} due to missing data", modifiedSymbol);
                    }
                }
            }
        } catch (IOException | ParseException e) {
            logger.error("An error occurred while updating stock data", e);
        }
    }
}