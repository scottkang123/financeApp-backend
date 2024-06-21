package com.finance.app.controller;

import com.finance.app.dto.StockDTO;
import com.finance.app.service.StockService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stock")
@RequiredArgsConstructor
@Tag(name = "Stock")
public class StockController {

    private static final Logger logger = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private StockService stockService;

    private static final long REQUEST_INTERVAL_MS = 60000; // 60 seconds

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
                String modifiedSymbol = symbol;
                //There are symbols that need to be changed
                if(symbol.equalsIgnoreCase("BRK.B")){
                    modifiedSymbol = "BRK-B";
                }
                if(count > 20)
                    break;
                if (stockService.isStockDataMissing(modifiedSymbol)) {
                    StockDTO stockDTO = stockService.fetchStockDataFromAlphaVantage(modifiedSymbol);
                    if(stockDTO.getName() == null){
                        logger.info("skipping because DTO had missing values especially in null -> could be some random error");
                    }else{
                        stockService.saveStockData(stockDTO);
                    }
                    Thread.sleep(REQUEST_INTERVAL_MS);
                    count++;
                }


            }
            logger.info("Data fetching and saving completed successfully");

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("An error occurred while fetching or saving stock data", e);
            Thread.currentThread().interrupt();
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/top500-by-peratio")
    public ResponseEntity<List<Object[]>> getTop500StocksByPERatio() {
        List<Object[]> stocks = stockService.getFirst500StocksByPERatio();
        return ResponseEntity.ok(stocks);
    }




}