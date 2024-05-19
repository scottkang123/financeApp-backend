package com.finance.app.scheduler;

import com.finance.app.dto.StockDTO;
import com.finance.app.model.Stock;
import com.finance.app.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class StockScheduler {

    @Autowired
    private StockService stockService;

    @Scheduled(cron = "0 0 0 * * ?") // This cron expression means midnight every day
    public void updateStockData() {
        List<StockDTO> stockDTOs = stockService.fetchStockData();
        stockService.saveStockData(stockDTOs);
    }
}