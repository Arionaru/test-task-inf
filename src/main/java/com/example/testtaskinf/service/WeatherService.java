package com.example.testtaskinf.service;

import com.example.testtaskinf.domain.Weather;

import java.time.LocalDate;
import java.util.List;

public interface WeatherService {
    void save(Weather weather);

    void aggregateWeather();

    List<Weather> getWeatherByDay(String city, String country, LocalDate localDate);

    Weather getWeatherNow(String city, String country);
}
