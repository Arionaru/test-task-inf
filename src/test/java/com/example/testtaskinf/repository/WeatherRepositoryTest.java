package com.example.testtaskinf.repository;

import com.example.testtaskinf.AbstractTest;
import com.example.testtaskinf.domain.Weather;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WeatherRepositoryTest extends AbstractTest {
    @Autowired
    WeatherRepository weatherRepository;

    @Test
    void getWeatherNow() {
        Weather oldWeather = Weather.builder().country("USA").city("New-York")
                .temperature(15.0).receivingTime(LocalDateTime.now().minusHours(1)).build();
        Weather newWeather = Weather.builder().country("USA").city("New-York")
                .temperature(16.0).receivingTime(LocalDateTime.now()).build();
        Weather anotherCityWeather = Weather.builder().country("USA").city("Atlanta")
                .temperature(17.0).receivingTime(LocalDateTime.now()).build();
        weatherRepository.saveAll(List.of(oldWeather, newWeather, anotherCityWeather));
        Weather weatherNow = weatherRepository.findFirstByCityAndCountryOrderByReceivingTimeDesc("New-York", "USA").get();
        assertThat(weatherNow.getTemperature()).isEqualTo(16.0);
    }

    @Test
    void getWeatherByDay() {
        Weather w1 = Weather.builder().country("USA").city("New-York")
                .temperature(15.0).receivingTime(LocalDateTime.now().minusHours(1)).build();
        Weather w2 = Weather.builder().country("USA").city("New-York")
                .temperature(16.0).receivingTime(LocalDateTime.now()).build();
        Weather w3 = Weather.builder().country("USA").city("New-York")
                .temperature(17.0).receivingTime(LocalDateTime.now().minusDays(1)).build();
        weatherRepository.saveAll(List.of(w1, w2, w3));
        List<Weather> todayWeather = weatherRepository.findWeatherByDay("New-York", "USA", LocalDate.now().toString());
        assertThat(todayWeather).hasSize(2);
    }

}