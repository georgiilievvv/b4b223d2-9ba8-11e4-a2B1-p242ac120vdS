package com.covidStats.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Entity class representing the COUNTRY_STATISTICS table.
 */
@Data
@NoArgsConstructor
@Entity(name = "COUNTRY_STATISTICS")
public class CountryCovidStatisticsEntity implements Serializable {

    @Id
    @Column(name = "COUNTRY_CODE")
    private String countryCode;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "SLUG")
    private String slug;

    @Column(name = "NEW_CONFIRMED")
    private int newConfirmed;

    @Column(name = "TOTAL_CONFIRMED")
    private int totalConfirmed;

    @Column(name = "NEW_DEATHS")
    private int newDeaths;

    @Column(name = "TOTAL_DEATHS")
    private int totalDeaths;

    @Column(name = "NEW_RECOVERED")
    private int newRecovered;

    @Column(name = "TOTAL_RECOVERED")
    private int totalRecovered;

    @Column(name = "STATISTIC_DATE")
    private LocalDateTime date;
}
