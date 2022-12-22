package com.covidStats.client;

import com.covidStats.BaseUnitTest;
import com.covidStats.dto.CountryCovidStatisticsDto;
import com.covidStats.dto.CovidSummaryWrapper;
import com.covidStats.repository.CovidSummaryRepository;
import com.covidStats.service.CovidSummaryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.MissingRequiredPropertiesException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link CovidApiClient}
 */
class CovidApiClientTest extends BaseUnitTest {
    private static final String COVID_SUMMARY_URL = "https://api.covid19api.com/summary";

    @InjectMocks
    private CovidApiClient covidApiClient;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private ResponseEntity<CovidSummaryWrapper> responseEntity;
    @Mock
    private CovidSummaryWrapper wrapperDto;
    @Mock
    private CountryCovidStatisticsDto dto;

    @Test
    void shouldReturnCollectionOfDtoWhenDataIsReturnedByRestTemplate() {
        when(restTemplate.getForEntity(COVID_SUMMARY_URL, CovidSummaryWrapper.class)).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(wrapperDto);
        when(wrapperDto.getCountries()).thenReturn(Collections.singletonList(dto));

        CovidSummaryWrapper resultWrapperDto = covidApiClient.fetchCovidSummary();

        assertEquals(wrapperDto, resultWrapperDto);
        verify(restTemplate).getForEntity(eq(COVID_SUMMARY_URL), eq(CovidSummaryWrapper.class));
    }

    @Test
    void shouldThrowMissingRequiredPropertiesExceptionWhenNoResponseBodyIsPresent() {
        when(restTemplate.getForEntity(COVID_SUMMARY_URL, CovidSummaryWrapper.class)).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(null);

        assertThrows(MissingRequiredPropertiesException.class, () -> covidApiClient.fetchCovidSummary());
    }

    @Test
    void shouldThrowMissingRequiredPropertiesExceptionWhenCountyCollectionIsEmpty() {
        when(restTemplate.getForEntity(COVID_SUMMARY_URL, CovidSummaryWrapper.class)).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(wrapperDto);
        when(wrapperDto.getCountries()).thenReturn(Collections.emptyList());

        assertThrows(MissingRequiredPropertiesException.class, () -> covidApiClient.fetchCovidSummary());
    }

    @Test
    void shouldThrowMissingRequiredPropertiesExceptionWhenNoCountyCollectionIsPresent() {
        when(restTemplate.getForEntity(COVID_SUMMARY_URL, CovidSummaryWrapper.class)).thenReturn(responseEntity);
        when(responseEntity.getBody()).thenReturn(wrapperDto);
        when(wrapperDto.getCountries()).thenReturn(null);

        assertThrows(MissingRequiredPropertiesException.class, () -> covidApiClient.fetchCovidSummary());
    }
}