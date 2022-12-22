package com.covidStats.integrationTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests.
 */
@ActiveProfiles("default")
@SpringBootTest
@AutoConfigureMockMvc
class ApplicationIntegrationTests {
    private static final String COUNTRY_PATH = "/country/";

    private static final String NON_EXISTING_SHORT_CODE = "QW";

    private static final String BULGARIA_SHORT_CODE = "BG";
    private static final String BULGARIA_NAME = "Bulgaria";

    private static final String NIGERIA_SHORT_CODE = "NG";
    private static final String NIGERIA_NAME = "Nigeria";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnOkAndBulgariaPropertiesWhenAddingBulgariaShortCodeInPath() throws Exception {
        this.mockMvc.perform(get(COUNTRY_PATH + BULGARIA_SHORT_CODE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Date", containsString(LocalDate.now().toString())))
                .andExpect(jsonPath("$.CountryCode", is(BULGARIA_SHORT_CODE)))
                .andExpect(jsonPath("$.Country", is(BULGARIA_NAME)));
    }

    @Test
    void shouldReturnOkAndNigeriaPropertiesWhenAddingNigeriaShortCodeInPath() throws Exception {
        this.mockMvc.perform(get(COUNTRY_PATH + NIGERIA_SHORT_CODE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Date", containsString(LocalDate.now().toString())))
                .andExpect(jsonPath("$.CountryCode", is(NIGERIA_SHORT_CODE)))
                .andExpect(jsonPath("$.Country", is(NIGERIA_NAME)));
    }

    @Test
    void shouldReturnNotFoundWhenNonExistingShortCodeIsInPath() throws Exception {
        this.mockMvc.perform(get(COUNTRY_PATH + NON_EXISTING_SHORT_CODE))
                .andExpect(status().isNotFound());
    }
}