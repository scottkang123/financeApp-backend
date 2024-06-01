package com.finance.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.app.dto.StockDTO;
import com.finance.app.model.Stock;
import com.finance.app.repo.StockRepo;
import com.finance.app.transformer.StockTransformer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StockService {

    @Value("${alpha.vantage.api.key}")
    private String alphaVantageApiKey;

    @Value("${alpha.vantage.api.url}")
    private String alphaVantageApiUrl;

    private static final Logger logger = LoggerFactory.getLogger(StockService.class);

    @Autowired
    private StockRepo stockRepo;

    @Autowired
    private StockTransformer stockTransformer;

    @Transactional
    public void saveStockData(StockDTO stockDTO) {
        Stock stock = stockTransformer.transformDtoM(stockDTO);
        stockRepo.save(stock);
    }

    public boolean isStockDataMissing(String symbol) {
        Stock stock = stockRepo.findBySymbol(symbol);
        return stock == null;
    }

    public StockDTO fetchStockDataFromAlphaVantage(String symbol) throws IOException, ParseException {

        try{
            RestTemplate restTemplate = new RestTemplate();
            String url = String.format(alphaVantageApiUrl, symbol, alphaVantageApiKey);

            String response = restTemplate.getForObject(url, String.class);

            logger.info(response);

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> jsonMap = objectMapper.readValue(response, Map.class);

            logger.info(jsonMap.toString());
            return StockTransformer.mapJsonToStockDTO(jsonMap);

        }catch(IOException | ParseException e){
            // Log the exception
            logger.error("Error fetching stock data from Alpha Vantage for symbol {}", symbol, e);
            throw e;
        }

    }

    public List<String> fetchSp500Symbols() throws IOException {
        String url = "https://www.slickcharts.com/sp500";
        Document document = Jsoup.connect(url).get();
        Elements rows = document.select("table.table-hover tbody tr");

        List<String> symbols = new ArrayList<>();
        for (Element row : rows) {
            Elements cols = row.select("td");
            if (cols.size() > 1) {
                symbols.add(cols.get(2).text());  // Assuming the symbol is in the third column
            }
        }

        return symbols;
    }
}

