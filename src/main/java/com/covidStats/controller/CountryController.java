package com.covidStats.controller;

import com.covidStats.dto.CountryCovidStatisticsDto;
import com.covidStats.service.CovidSummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

/**
 * Controller class responsible for processing incoming REST API
 * requests for covid related statistics by country.
 */
@RestController
@RequiredArgsConstructor
public class CountryController {
    private final CovidSummaryService covidSummaryService;

    @GetMapping("country/{countryCode}")
    public ResponseEntity<CountryCovidStatisticsDto> getCountrySummaryByCode(@PathVariable String countryCode) {
        CountryCovidStatisticsDto countryCovidStatisticsDto = covidSummaryService.getByCountryCode(countryCode.toUpperCase());
        return ResponseEntity.ok(countryCovidStatisticsDto);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entityNotFoundExceptionHandler() {
        return ResponseEntity.notFound().build();
    }
}
