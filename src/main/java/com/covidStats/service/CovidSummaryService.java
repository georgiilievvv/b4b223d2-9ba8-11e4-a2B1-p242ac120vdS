package com.covidStats.service;

import com.covidStats.dto.CountryCovidStatisticsDto;
import com.covidStats.entity.CountryCovidStatisticsEntity;
import com.covidStats.repository.CovidSummaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class managing covid related statistics by country.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CovidSummaryService {

    private final CovidSummaryRepository repository;
    private final ModelMapper mapper;

    public void saveAll(List<CountryCovidStatisticsDto> countryCovidStatisticsDtoList) {
        List<CountryCovidStatisticsEntity> countryStatisticsEntities = countryCovidStatisticsDtoList
                .stream()
                .map(dto -> mapper.map(dto, CountryCovidStatisticsEntity.class))
                .collect(Collectors.toList());

        repository.saveAll(countryStatisticsEntities);
        log.info("action=saveAll result=success countries={}", countryStatisticsEntities.size());
    }

    public CountryCovidStatisticsDto getByCountryCode(String countryCode) {
        CountryCovidStatisticsEntity countryCovidStatisticsEntity = repository.findById(countryCode)
                .orElseThrow(() -> new EntityNotFoundException(countryCode));

        log.info("action=getByCountryCode result=success countryCode={}", countryCode);
        return mapper.map(countryCovidStatisticsEntity, CountryCovidStatisticsDto.class);
    }
}
