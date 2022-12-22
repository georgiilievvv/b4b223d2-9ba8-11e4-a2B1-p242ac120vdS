package com.covidStats;

import com.covidStats.task.DataPopulationTask;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

/**
 * Base class for all unit test classes
 */
@ActiveProfiles("unit-test")
@SpringBootTest
public class BaseUnitTest {

    // mock bean of DataPopulationTask to skip post construct data population
    @MockBean
    private DataPopulationTask mockTask;
}
