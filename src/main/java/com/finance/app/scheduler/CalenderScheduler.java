package com.finance.app.scheduler;

import com.finance.app.service.EarningCalenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class CalenderScheduler {

    private static final Logger logger = LoggerFactory.getLogger(CalenderScheduler.class);

    @Autowired
    private EarningCalenderService earningCalenderService;

    // Schedule the task to run once a year at midnight on January 1st
    @Scheduled(cron = "0 0 0 1 1 *")
    public void updateEarningsCalendar() {
        try {
            earningCalenderService.saveFilteredEarningsCalendar();
            logger.info("Earnings calendar updated successfully.");
        } catch (Exception e) {
            logger.error("Error updating earnings calendar", e);
        }
    }

}
