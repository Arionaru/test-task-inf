package com.example.testtaskinf.service.impl;

import com.example.testtaskinf.AbstractTest;
import com.example.testtaskinf.client.WeatherApiClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

class WeatherApiServiceTest extends AbstractTest {
    @Autowired
    WeatherApiService weatherApiService;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    WeatherApiClient weatherApiClient;

    @Test
    void getTempSuccessTest() throws IOException {
        InputStream resource = new ClassPathResource("json/weatherapi.json").getInputStream();
        JsonNode jsonNode = objectMapper.readTree(resource);
        Mockito.when(weatherApiClient.getData(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(jsonNode);
        double temperature = weatherApiService.getTemperature("50", "50");
        assertThat(temperature).isEqualTo(19.0);
    }

    @Test
    void getTempFailedTest() throws IOException {
        InputStream resource = new ClassPathResource("json/weatherapierror.json").getInputStream();
        JsonNode jsonNode = objectMapper.readTree(resource);
        Mockito.when(weatherApiClient.getData(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(jsonNode);
        Double temperature = weatherApiService.getTemperature("50", "50");
        assertThat(temperature).isNull();
    }
}