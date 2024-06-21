package com.finance.app.service;

import com.finance.app.dto.EarningCalenderDTO;
import com.finance.app.model.EarningsCalender;
import com.finance.app.model.Stock;
import com.finance.app.repo.EarningsCalenderRepo;
import com.finance.app.repo.StockRepo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/* Not working for some reason. The API seems to have a problem */
@Service
public class EarningCalenderService {

    private static final Logger logger = LoggerFactory.getLogger(EarningCalenderService.class);

    @Autowired
    private StockRepo stockRepository;

    @Autowired
    private EarningsCalenderRepo earningsCalenderRepository;

    @Value("${alpha.vantage.api.key_2}")
    private String alphaVantageApiKey;

    private final String API_URL = "https://www.alphavantage.co/query?function=EARNINGS_CALENDAR&horizon=12month&apikey=%s";

    public List<EarningCalenderDTO> fetchEarningsCalendar() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format(API_URL, alphaVantageApiKey);
        logger.info("Fetching data from: " + url);

        ResponseEntity<byte[]> response;
        try {
            response = restTemplate.exchange(url, HttpMethod.GET, null, byte[].class);
        } catch (Exception e) {
            logger.error("Error fetching data from API: ", e);
            throw new IllegalStateException("Failed to fetch data from API", e);
        }

        byte[] responseData = response.getBody();
        if (responseData == null) {
            logger.error("Empty response body");
            throw new IllegalStateException("Empty response body");
        }

        Path tempFile = Paths.get("earnings_calendar.csv");

        // Delete existing file if it exists
        if (Files.exists(tempFile)) {
            Files.delete(tempFile);
        }

        Files.write(tempFile, responseData);
        logger.info("Received CSV data and saved at: " + tempFile);

        List<EarningCalenderDTO> earningCalenderDTOs = parseCsvFile(tempFile);

        logger.info("Parsed EarningCalenderDTOs: " + earningCalenderDTOs);
        return earningCalenderDTOs;
    }

    private List<EarningCalenderDTO> parseCsvFile(Path csvFilePath) throws Exception {
        List<EarningCalenderDTO> earningCalenderDTOs = new ArrayList<>();

        try (Reader reader = Files.newBufferedReader(csvFilePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            for (CSVRecord record : csvParser) {
                EarningCalenderDTO dto = EarningCalenderDTO.builder()
                        .symbol(record.get("symbol"))
                        .name(record.get("name"))
                        .reportDate(record.get("reportDate"))
                        .fiscalDateEnding(record.get("fiscalDateEnding"))
                        .estimate(record.get("estimate"))
                        .currency(record.get("currency"))
                        .build();
                logger.debug("Parsed DTO: " + dto);
                earningCalenderDTOs.add(dto);
            }
        } catch (Exception e) {
            logger.error("Error parsing CSV file: ", e);
            throw e;
        }

        return earningCalenderDTOs;
    }

    public void saveFilteredEarningsCalendar() throws Exception {
        List<String> stockSymbols = stockRepository.findAllSymbols();
        List<EarningCalenderDTO> earningsCalendarDTOList = fetchEarningsCalendar();

        logger.info("Stock Symbols: " + stockSymbols);
        logger.info("Earnings Calendar DTOs: " + earningsCalendarDTOList);

        List<EarningsCalender> entries = earningsCalendarDTOList.stream()
                .filter(dto -> stockSymbols.contains(dto.getSymbol()))
                .map(dto -> {
                    Stock stock = stockRepository.findBySymbol(dto.getSymbol());

                    if (stock == null) {
                        logger.error("Stock not found for symbol: " + dto.getSymbol());
                        return null;
                    }

                    EarningsCalender earningsCalender = EarningsCalender.builder()
                            .stock(stock)
                            .reportDate(LocalDate.parse(dto.getReportDate()))
                            .fiscalDateEnding(LocalDate.parse(dto.getFiscalDateEnding()))
                            .estimate(dto.getEstimate())
                            .currency(dto.getCurrency())
                            .build();

                    logger.info("Created EarningsCalender: " + earningsCalender);
                    return earningsCalender;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        logger.info("Saving Entries: " + entries);
        earningsCalenderRepository.saveAll(entries);
    }
}
