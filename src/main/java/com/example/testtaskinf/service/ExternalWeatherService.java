package com.example.testtaskinf.service;

import com.example.testtaskinf.domain.Weather;

public interface ExternalWeatherService {
    double getTemperature(String lat, String lon);
}
