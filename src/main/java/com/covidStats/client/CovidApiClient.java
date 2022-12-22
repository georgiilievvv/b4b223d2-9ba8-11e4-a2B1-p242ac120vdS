package com.covidStats.client;

import com.covidStats.dto.CovidSummaryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.MissingRequiredPropertiesException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Client class for retrieving covid statistics data from a covid19 API.
 */
@Slf4j
@Component
public class CovidApiClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String COVID_SUMMARY_URL = "https://api.covid19api.com/summary";

    /**
     *  Calling covid19 API and retrieving covid19 statistics summary for all countries.
     *
     * @return {@link CovidSummaryWrapper} if this list contains the specified element
     * @throws MissingRequiredPropertiesException if part of the response body is missing.
     */
    @Retryable(maxAttempts=60, value = Exception.class,
            backoff = @Backoff(delay = 10000))
    public CovidSummaryWrapper fetchCovidSummary() {
        log.info("action=fetchCovidSummary ulr={}", COVID_SUMMARY_URL);

        CovidSummaryWrapper covidSummaryWrapper = restTemplate
                .getForEntity(COVID_SUMMARY_URL, CovidSummaryWrapper.class)
                .getBody();

        if (covidSummaryWrapper == null || covidSummaryWrapper.getCountries() == null
                || covidSummaryWrapper.getCountries().isEmpty()) {
            log.error("action=fetchCovidSummary error='missing data'");
            throw new MissingRequiredPropertiesException();
        }
        log.info("action=fetchCovidSummary result=success countries={}", covidSummaryWrapper.getCountries().size());
        return covidSummaryWrapper;
    }
}
