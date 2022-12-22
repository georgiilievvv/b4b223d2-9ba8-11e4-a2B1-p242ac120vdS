package com.covidStats.repository;

import com.covidStats.entity.CountryCovidStatisticsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for generic CRUD operations on a repository for country statistics.
 */
@Repository
public interface CovidSummaryRepository extends CrudRepository<CountryCovidStatisticsEntity, String>{

}
