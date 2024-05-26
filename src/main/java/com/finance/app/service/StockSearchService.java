package com.finance.app.service;

import com.finance.app.dto.StockDTO;
import com.finance.app.model.Stock;
import com.finance.app.repo.StockRepo;
import com.finance.app.serializable.DTO;
import com.finance.app.transformer.StockTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StockSearchService implements SearchService {

    @Autowired
    private StockRepo stockRepo;

    @Autowired
    private StockTransformer transformer;

    @Override
    public List<DTO> getSearchResults(String query) {
        Set<String> seen = new HashSet<>();
        List<StockDTO> result = new ArrayList<>();

        // Break up query into keywords
        List<String> keywords = processQuery(query);

        for (String key : keywords) {
            List<StockDTO> keyResult = searchStocksByKeyword(key);
            for (StockDTO dto : keyResult) {
                // TODO: Checking duplicates by stock symbol; Better way?
                String symbol = dto.getSymbol();
                if (!seen.contains(symbol)) {
                    result.add(dto);
                    seen.add(symbol);
                }
            }
        }

        return new ArrayList<>(result);
    }

    // Simple query breakup logic
    private List<String> processQuery(String query) {
        String[] keywords = query.split(DELIMITER);
        return Arrays.asList(keywords);
    }

    private List<StockDTO> searchStocksByKeyword(String keyword) {
        List<Stock> temp = stockRepo.findbyNameContainingKeywordIgnoreCase(keyword);
        return stockRepo.findbyNameContainingKeywordIgnoreCase(keyword)
                .stream()
                .map(transformer::transformMtoD)
                .collect(Collectors.toList());
    }
}
