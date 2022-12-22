package com.covidStats.controller;

import com.covidStats.dto.CountryCovidStatisticsDto;
import com.covidStats.service.CovidSummaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestController
@RequiredArgsConstructor
public class CountryCovidStatisticsController {
    private final CovidSummaryService covidSummaryService;

    @GetMapping("country/{countryCode}")
    public ResponseEntity<CountryCovidStatisticsDto> getCountrySummaryByCode(@PathVariable String countryCode) {
        CountryCovidStatisticsDto countryCovidStatisticsDto = covidSummaryService.getByCountryCode(countryCode.toUpperCase());
        return ResponseEntity.ok(countryCovidStatisticsDto);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entityNotFoundExceptionHandler(EntityNotFoundException e) {
        log.error("action=entityNotFoundExceptionHandler result=error id={}", e.getMessage());
        return ResponseEntity.notFound().build();
    }
}
