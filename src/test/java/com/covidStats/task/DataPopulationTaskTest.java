package com.covidStats.task;

import com.covidStats.BaseUnitTest;
import com.covidStats.client.CovidApiClient;
import com.covidStats.dto.CountryCovidStatisticsDto;
import com.covidStats.dto.CovidSummaryWrapper;
import com.covidStats.service.CovidSummaryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.env.MissingRequiredPropertiesException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link DataPopulationTask}
 */
class DataPopulationTaskTest extends BaseUnitTest {

    @InjectMocks
    private DataPopulationTask task;
    @Mock
    private CovidApiClient covidApiClient;
    @Mock
    private CovidSummaryService covidSummaryService;
    @Mock
    private static CovidSummaryWrapper wrapper;
    @Mock
    private static List<CountryCovidStatisticsDto> dtoList;

    @Test
    void shouldSaveDataWhenListHasData() {
        when(covidApiClient.fetchCovidSummary()).thenReturn(wrapper);
        when(wrapper.getCountries()).thenReturn(dtoList);

        task.populateCovidData();

        verify(covidApiClient).fetchCovidSummary();
        verify(covidSummaryService).saveAll(dtoList);
    }
    @Test
    void shouldNotSaveDataWhenMissingRequiredPropertiesExceptionIsThrown() {
        when(covidApiClient.fetchCovidSummary()).thenThrow(MissingRequiredPropertiesException.class);
        when(wrapper.getCountries()).thenReturn(dtoList);

        assertThrows(MissingRequiredPropertiesException.class, () -> task.populateCovidData());

        verify(covidApiClient).fetchCovidSummary();
        verify(covidSummaryService, never()).saveAll(dtoList);
    }
}