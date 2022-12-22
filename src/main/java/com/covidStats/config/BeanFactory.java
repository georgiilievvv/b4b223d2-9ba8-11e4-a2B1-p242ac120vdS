package com.covidStats.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class defining additional beans.
 */
@Configuration
public class BeanFactory {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
