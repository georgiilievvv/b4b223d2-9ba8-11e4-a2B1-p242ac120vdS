package com.covidStats.task;

import com.covidStats.client.CovidApiClient;
import com.covidStats.dto.CovidSummaryWrapper;
import com.covidStats.service.CovidSummaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Scheduled task class populating data in the database.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DataPopulationTask {
    private static final String HOURLY_CRON_SCHEDULE = "0 0 * * * ?";

    private final CovidApiClient covidApiClient;
    private final CovidSummaryService covidSummaryService;

    /**
     * Scheduled task configured to populate country covid statistics data
     * after dependency injection (application will not be up and running
     * if it fails to execute) and every hour.
     */
    @PostConstruct
    @Scheduled(cron = HOURLY_CRON_SCHEDULE)
    public void populateCovidData() {
        CovidSummaryWrapper covidSummaryWrapper = covidApiClient.fetchCovidSummary();
        covidSummaryService.saveAll(covidSummaryWrapper.getCountries());
        log.info("action=populateDailyCovidData result=success");
    }
}