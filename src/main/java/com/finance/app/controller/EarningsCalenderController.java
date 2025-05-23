package com.finance.app.controller;

import com.finance.app.dto.EarningCalenderDTO;
import com.finance.app.service.EarningCalenderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("calendar")
@RequiredArgsConstructor
@Tag(name = "EarningsCalender")
public class EarningsCalenderController {

    private static final Logger logger = LoggerFactory.getLogger(EarningsCalenderController.class);

    @Autowired
    private EarningCalenderService earningCalenderService;

    @PostMapping("/fetch-and-save")
    public void fetchAndSaveEarningsCalendar() {
        try {
            logger.info("Fetching data");
            earningCalenderService.saveFilteredEarningsCalendar();
        } catch (Exception e) {
            logger.error("Error saving earnings calendar", e);
        }
    }

    @GetMapping("/retrieve-earnings-calendar")
    public ResponseEntity<List<EarningCalenderDTO>> retreiveEarningsCalendar() {
        try {
            logger.info("Fetching earnings calendar data");
            List<EarningCalenderDTO> earningsCalendar = earningCalenderService.retrieveEarningsCalendar();
            return ResponseEntity.ok(earningsCalendar);
        } catch (Exception e) {
            logger.error("Error saving earnings calendar", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
