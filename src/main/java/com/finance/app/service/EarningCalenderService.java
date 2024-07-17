package com.finance.app.service;

import com.finance.app.dto.EarningCalenderDTO;
import com.finance.app.model.EarningsCalender;
import com.finance.app.model.Stock;
import com.finance.app.repo.EarningsCalenderRepo;
import com.finance.app.repo.StockRepo;
import jakarta.annotation.PostConstruct;
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

    @Value("${csv.earnings.calender.file.path}")
    private String csvFilePath;

    private final String API_URL = "https://www.alphavantage.co/query?function=EARNINGS_CALENDAR&horizon=12month&apikey=%s";

    private Path LOCAL_CSV_PATH;

    @PostConstruct
    public void init() {
        LOCAL_CSV_PATH = Paths.get(csvFilePath);
    }
    private List<EarningsCalender> parseAndFilterCsvFile(Path csvFilePath, List<String> stockSymbols) throws Exception {
        List<EarningsCalender> earningsCalenders = new ArrayList<>();

        try (Reader reader = Files.newBufferedReader(csvFilePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            for (CSVRecord record : csvParser) {
                String symbol = record.get("symbol");

                if (stockSymbols.contains(symbol)) {
                    Stock stock = stockRepository.findBySymbol(symbol);
                    if (stock != null) {
                        EarningsCalender earningsCalender = EarningsCalender.builder()
                                .stock(stock)
                                .reportDate(LocalDate.parse(record.get("reportDate")))
                                .fiscalDateEnding(LocalDate.parse(record.get("fiscalDateEnding")))
                                .estimate(record.get("estimate"))
                                .currency(record.get("currency"))
                                .build();
                        earningsCalenders.add(earningsCalender);
                    } else {
                        logger.error("Stock not found for symbol: " + symbol);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error parsing CSV file: ", e);
            throw e;
        }

        return earningsCalenders;
    }


    public void saveFilteredEarningsCalendar() throws Exception {
        List<String> stockSymbols = stockRepository.findAllSymbols();
        List<EarningsCalender> earningsCalenders = parseAndFilterCsvFile(LOCAL_CSV_PATH, stockSymbols);

        logger.info("Saving Entries: " + earningsCalenders);
        earningsCalenderRepository.saveAll(earningsCalenders);
    }

    public List<EarningCalenderDTO> retrieveEarningsCalendar() {
        List<EarningsCalender> earningsCalenders = earningsCalenderRepository.findAll(); // Adjust as per your repository method

        //Create a file in Transformer folder -> EarningCalendarTransformer or smth
        return earningsCalenders.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private EarningCalenderDTO convertToDTO(EarningsCalender earningsCalender) {
        return EarningCalenderDTO.builder()
                .symbol(earningsCalender.getStock().getSymbol())
                .name(earningsCalender.getStock().getName()) // Adjust as per your Stock entity
                .reportDate(earningsCalender.getReportDate())
                .fiscalDateEnding(earningsCalender.getFiscalDateEnding())
                .estimate(earningsCalender.getEstimate())
                .currency(earningsCalender.getCurrency())
                .build();
    }

}
