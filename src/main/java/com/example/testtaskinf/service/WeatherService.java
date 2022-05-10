package com.example.testtaskinf.service;

import com.example.testtaskinf.domain.Weather;

public interface WeatherService {
    void save(Weather weather);

    void aggregateWeather();
}
