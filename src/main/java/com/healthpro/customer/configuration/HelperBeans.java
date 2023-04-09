package com.healthpro.customer.configuration;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelperBeans {

    @Bean
    public ObjectMapper createObjectMapper() {
        return new ObjectMapper();
    }
}
