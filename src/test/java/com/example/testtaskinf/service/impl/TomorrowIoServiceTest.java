package com.example.testtaskinf.service.impl;

import com.example.testtaskinf.AbstractTest;
import com.example.testtaskinf.client.TomorrowIoClient;
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

class TomorrowIoServiceTest extends AbstractTest {
    @Autowired
    TomorrowIoService tomorrowIoService;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    TomorrowIoClient tomorrowIoClient;

    @Test
    void getTempSuccessTest() throws IOException {
        InputStream resource = new ClassPathResource("json/tomorrowio.json").getInputStream();
        JsonNode jsonNode = objectMapper.readTree(resource);
        Mockito.when(tomorrowIoClient.getData(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(jsonNode);
        double temperature = tomorrowIoService.getTemperature("50", "50");
        assertThat(temperature).isEqualTo(16.55);
    }

    @Test
    void getTempFailedTest() throws IOException {
        InputStream resource = new ClassPathResource("json/tomorrowio.json").getInputStream();
        JsonNode jsonNode = objectMapper.readTree(resource);
        Mockito.when(tomorrowIoClient.getData(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(jsonNode);
        Double temperature = tomorrowIoService.getTemperature("50", "50");
        assertThat(temperature).isNull();
    }
}