package com.example.testtaskinf.service.impl;

import com.example.testtaskinf.AbstractTest;
import com.example.testtaskinf.configuration.ProjectProperties;
import com.example.testtaskinf.domain.Weather;
import com.example.testtaskinf.domain.positionstack.PositionStackResponse;
import com.example.testtaskinf.domain.positionstack.StackData;
import com.example.testtaskinf.repository.WeatherRepository;
import com.example.testtaskinf.service.PositionStackService;
import com.example.testtaskinf.service.WeatherService;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class WeatherServiceImplTest extends AbstractTest {
    @Autowired
    WeatherService weatherService;
    @Autowired
    WeatherRepository weatherRepository;
    @Autowired
    ProjectProperties projectProperties;
    @MockBean(name = "positionStackService")
    PositionStackService positionStackService;
    @MockBean
    OpenWeatherMapService openWeatherMapService;
    @MockBean
    TomorrowIoService tomorrowIoService;
    @MockBean
    WeatherApiService weatherApiService;

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

    @Test
    void aggregateSimpleTest() {
        Mockito.when(positionStackService.getCitiesMap()).thenReturn(getTestMap());
        Mockito.when(openWeatherMapService.getTemperature(Mockito.anyString(), Mockito.anyString()))
                .thenReturn((double) (10));
        Mockito.when(tomorrowIoService.getTemperature(Mockito.anyString(), Mockito.anyString()))
                .thenReturn((double) (20));
        Mockito.when(weatherApiService.getTemperature(Mockito.anyString(), Mockito.anyString()))
                .thenReturn((double) (30));
        weatherService.aggregateWeather();
        List<Weather> weathers = weatherRepository.findAll();
        assertThat(weathers).hasSize(projectProperties.getCities().size());
        weathers.forEach(weather -> assertThat(weather.getTemperature()).isEqualTo(20));
    }

    @Test
    void aggregateWithNull() {
        Mockito.when(positionStackService.getCitiesMap()).thenReturn(getTestMap());
        Mockito.when(openWeatherMapService.getTemperature(Mockito.anyString(), Mockito.anyString()))
                .thenReturn((double) (10));
        Mockito.when(tomorrowIoService.getTemperature(Mockito.anyString(), Mockito.anyString()))
                .thenReturn((double) (20));
        Mockito.when(weatherApiService.getTemperature(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(null);
        weatherService.aggregateWeather();
        List<Weather> weathers = weatherRepository.findAll();
        assertThat(weathers).hasSize(projectProperties.getCities().size());
        weathers.forEach(weather -> assertThat(weather.getTemperature()).isEqualTo(15));
    }

    @NotNull
    private Map<String, PositionStackResponse> getTestMap() {
        return Map.of(
                "test1", PositionStackResponse.builder().data(List.of(StackData.builder().country("1").name("11").latitude("50").longitude("50").build())).build(),
                "test2", PositionStackResponse.builder().data(List.of(StackData.builder().country("2").name("22").latitude("50").longitude("50").build())).build(),
                "test3", PositionStackResponse.builder().data(List.of(StackData.builder().country("3").name("33").latitude("50").longitude("50").build())).build()
        );
    }
}