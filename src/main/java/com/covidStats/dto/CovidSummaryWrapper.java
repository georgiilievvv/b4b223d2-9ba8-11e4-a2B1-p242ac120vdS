package com.covidStats.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Wrapper class for the response body of covid summary.
 */
@Getter
@Setter
@NoArgsConstructor
public class CovidSummaryWrapper implements Serializable {

    @JsonProperty("Countries")
    private List<CountryCovidStatisticsDto> countries;
}
