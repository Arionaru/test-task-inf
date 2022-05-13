package com.example.testtaskinf.controller;

import com.example.testtaskinf.AbstractTest;
import com.example.testtaskinf.domain.Weather;
import com.example.testtaskinf.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
class WeatherControllerTest extends AbstractTest {
    @Autowired
    WeatherController weatherController;
    @Autowired
    WeatherService weatherService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getLastTempNoDataTest() throws Exception {
        mockMvc.perform(get("/api/v1/weather")
                .param("city", "Paris")
                .param("country", "France"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void getWithDateNoDataTest() throws Exception {
        mockMvc.perform(get("/api/v1/weather")
                .param("city", "Paris")
                .param("country", "France")
                .param("date", "2022-05-13"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void getLastTempWithData() throws Exception {
        Weather w1 = Weather.builder().city("testCity").country("testCountry").temperature(5.0)
                .receivingTime(LocalDateTime.of(2022, 5, 13, 12, 30)).build();
        Weather w2 = Weather.builder().city("testCity").country("testCountry").temperature(5.5)
                .receivingTime(LocalDateTime.of(2022, 5, 13, 12, 40)).build();
        weatherService.save(w1);
        weatherService.save(w2);
        mockMvc.perform(get("/api/v1/weather")
                .param("city", "testCity")
                .param("country", "testCountry"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].country").value("testCountry"))
                .andExpect(jsonPath("$.[0].city").value("testCity"))
                .andExpect(jsonPath("$.[0].temperature").value("5.5"))
                .andExpect(jsonPath("$.[0].receivingTime").value("2022-05-13T12:40:00"))

        ;
    }

    @Test
    void getDayTempWithData() throws Exception {
        Weather w1 = Weather.builder().city("testCity").country("testCountry").temperature(5.0)
                .receivingTime(LocalDateTime.of(2022, 5, 13, 12, 30)).build();
        Weather w2 = Weather.builder().city("testCity").country("testCountry").temperature(5.5)
                .receivingTime(LocalDateTime.of(2022, 5, 13, 12, 40)).build();
        Weather w3 = Weather.builder().city("testCity2").country("testCountry2").temperature(5.5)
                .receivingTime(LocalDateTime.of(2022, 5, 13, 12, 40)).build();
        weatherService.save(w1);
        weatherService.save(w2);
        weatherService.save(w3);
        mockMvc.perform(get("/api/v1/weather")
                .param("city", "testCity")
                .param("country", "testCountry")
                .param("date", "2022-05-13"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].country").value("testCountry"))
                .andExpect(jsonPath("$.[1].country").value("testCountry"))
        ;
    }
}