package com.finance.app.service;

import com.finance.app.dto.StockDTO;
import com.finance.app.model.Stock;
import com.finance.app.repo.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {

    private static final String API_URL = "https://financialmodelingprep.com/api/v3/stock/list?apikey=5BCLWBo3FYqhe9nJvegwJCM8L6aoaKwi";

    @Autowired
    private StockRepo stockRepo;
    public List<StockDTO> fetchStockData() {
        RestTemplate restTemplate = new RestTemplate();
        StockDTO[] stocks = restTemplate.getForObject(API_URL, StockDTO[].class);
        return Arrays.asList(stocks);
    }

    public void saveStockData(List<StockDTO> stockDTOs) {
        List<Stock> stocks = stockDTOs.stream()
                .limit(100)
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        stockRepo.saveAll(stocks);
    }

    private Stock convertToEntity(StockDTO stockDTO) {
        return Stock.builder()
                .name(stockDTO.getName())
                .symbol(stockDTO.getSymbol())
                .exchange(stockDTO.getExchange())
                .price(stockDTO.getPrice())
                .build();
    }
}

