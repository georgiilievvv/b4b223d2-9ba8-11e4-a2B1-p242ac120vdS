package com.covidStats.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO class representing covid statistics data by country.
 */
@Data
@NoArgsConstructor
public class CountryCovidStatisticsDto implements Serializable {

    @JsonProperty("CountryCode")
    private String countryCode;

    @JsonProperty("Country")
    private String country;

    @JsonProperty("Slug")
    private String slug;

    @JsonProperty("NewConfirmed")
    private int newConfirmed;

    @JsonProperty("TotalConfirmed")
    private int totalConfirmed;

    @JsonProperty("NewDeaths")
    private int newDeaths;

    @JsonProperty("TotalDeaths")
    private int totalDeaths;

    @JsonProperty("NewRecovered")
    private int newRecovered;

    @JsonProperty("TotalRecovered")
    private int totalRecovered;

    @JsonProperty("Date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime date;
}
