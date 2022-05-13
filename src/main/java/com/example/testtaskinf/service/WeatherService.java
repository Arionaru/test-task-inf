package com.example.testtaskinf.service;

import com.example.testtaskinf.domain.Weather;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WeatherService {
    void save(Weather weather);

    void aggregateWeather();

    List<Weather> getWeatherByDay(String city, String country, LocalDate localDate);

    Optional<Weather> getWeatherNow(String city, String country);
}
