package com.example.testtaskinf.service.impl;

import com.example.testtaskinf.AbstractTest;
import com.example.testtaskinf.client.OpenWeatherMapClient;
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

class OpenWeatherMapServiceTest extends AbstractTest {
    @Autowired
    OpenWeatherMapService openWeatherMapService;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    OpenWeatherMapClient openWeatherMapClient;

    @Test
    void getTempSuccessTest() throws IOException {
        InputStream resource = new ClassPathResource("json/openeweathermapcorrect.json").getInputStream();
        JsonNode jsonNode = objectMapper.readTree(resource);
        Mockito.when(openWeatherMapClient.getData(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(jsonNode);
        double temperature = openWeatherMapService.getTemperature("50", "50");
        assertThat(temperature).isEqualTo(18.04);
    }

    @Test
    void getTempFailedTest() throws IOException {
        InputStream resource = new ClassPathResource("json/openweathermaperror.json").getInputStream();
        JsonNode jsonNode = objectMapper.readTree(resource);
        Mockito.when(openWeatherMapClient.getData(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(jsonNode);
        Double temperature = openWeatherMapService.getTemperature("50", "50");
        assertThat(temperature).isNull();
    }

}