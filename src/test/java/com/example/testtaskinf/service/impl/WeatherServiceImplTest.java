package com.example.testtaskinf.service.impl;

import com.example.testtaskinf.domain.Weather;
import com.example.testtaskinf.repository.WeatherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WeatherServiceImplTest {

    @Autowired
    WeatherRepository weatherRepository;

    @Test
    void save() {
        Weather weather = Weather.builder()
                .country("testCountry")
                .city("testCity")
                .temperature(Double.valueOf("25.25"))
                .receivingTime(LocalDateTime.now())
                .build();
        weatherRepository.save(weather);
    }
}