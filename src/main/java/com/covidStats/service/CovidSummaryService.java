package com.covidStats.service;

import com.covidStats.dto.CountryCovidStatisticsDto;
import com.covidStats.entity.CountryCovidStatisticsEntity;
import com.covidStats.repository.CovidSummaryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class managing covid related statistics by country.
 */
@Service
@RequiredArgsConstructor
public class CovidSummaryService {

    private final CovidSummaryRepository repository;
    private final ModelMapper mapper = new ModelMapper();

    public void saveAll(List<CountryCovidStatisticsDto> countryCovidStatisticsDtoList) {
        List<CountryCovidStatisticsEntity> countryStatisticsEntities = countryCovidStatisticsDtoList
                .stream()
                .map(dto -> mapper.map(dto, CountryCovidStatisticsEntity.class))
                .collect(Collectors.toList());

        repository.saveAll(countryStatisticsEntities);
    }

    public CountryCovidStatisticsDto getByCountryCode(String countryCode) {
        CountryCovidStatisticsEntity countryCovidStatisticsEntity = repository.findById(countryCode)
                .orElseThrow(EntityNotFoundException::new);

        return mapper.map(countryCovidStatisticsEntity, CountryCovidStatisticsDto.class);
    }
}
