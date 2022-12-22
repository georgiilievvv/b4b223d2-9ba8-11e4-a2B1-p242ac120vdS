package com.covidStats.service;

import com.covidStats.dto.CountryCovidStatisticsDto;
import com.covidStats.entity.CountryCovidStatisticsEntity;
import com.covidStats.repository.CovidSummaryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link CovidSummaryService}
 */
@SpringBootTest
class CovidSummaryServiceTest {
    private static final String BG_COUNTY_CODE = "BG";


    @InjectMocks
    private CovidSummaryService service;

    @Mock
    private CovidSummaryRepository repository;
    @Mock
    private ModelMapper mapper;

    @Mock
    private CountryCovidStatisticsDto dto1;
    @Mock
    private CountryCovidStatisticsDto dto2;
    @Mock
    private CountryCovidStatisticsEntity entity1;
    @Mock
    private CountryCovidStatisticsEntity entity2;

    private static List<CountryCovidStatisticsDto> dtoList;
    private static List<CountryCovidStatisticsEntity> entityList ;

    @BeforeEach
    void beforeAll() {
        dtoList = List.of(dto1, dto2);
        entityList = List.of(entity1, entity2);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenNullEntityIsProvided() {
        when(mapper.map(dto1, CountryCovidStatisticsEntity.class)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> service.saveAll(dtoList));

        verify(repository, never()).saveAll(any());
        verify(mapper).map(eq(dto1), eq(CountryCovidStatisticsEntity.class));
        verify(mapper, never()).map(eq(dto2), eq(CountryCovidStatisticsEntity.class));
    }

    @Test
    void shouldSaveAllWhenListWithDtosIsProvided() {
        when(mapper.map(dto1, CountryCovidStatisticsEntity.class)).thenReturn(entity1);
        when(mapper.map(dto2, CountryCovidStatisticsEntity.class)).thenReturn(entity2);

        service.saveAll(dtoList);

        verify(repository).saveAll(eq(entityList));
        verify(mapper).map(eq(dto1), eq(CountryCovidStatisticsEntity.class));
        verify(mapper).map(eq(dto2), eq(CountryCovidStatisticsEntity.class));
    }

    @Test
    void shouldReturnDtoWhenRepositoryReturnsEntity() {
        when(repository.findById(BG_COUNTY_CODE)).thenReturn(Optional.of(entity1));
        when(mapper.map(entity1, CountryCovidStatisticsDto.class)).thenReturn(dto1);

        CountryCovidStatisticsDto resultDto = service.getByCountryCode(BG_COUNTY_CODE);

        assertEquals(resultDto, dto1);
        verify(repository).findById(eq(BG_COUNTY_CODE));
        verify(mapper).map(eq(entity1), eq(CountryCovidStatisticsDto.class));
    }

    @Test
    void shouldThrowEntityNotFoundExceptionWhenRepositoryReturnsEmptyOptional() {
        when(repository.findById(BG_COUNTY_CODE)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> service.getByCountryCode(BG_COUNTY_CODE));

        assertEquals(exception.getMessage(), BG_COUNTY_CODE);
        verify(repository).findById(eq(BG_COUNTY_CODE));
        verify(mapper, never());
    }
}