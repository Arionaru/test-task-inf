package com.example.testtaskinf.service.impl;

import com.example.testtaskinf.domain.Weather;
import com.example.testtaskinf.repository.WeatherRepository;
import com.example.testtaskinf.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private final WeatherRepository weatherRepository;

    @Override
    public void save(Weather weather) {
        weatherRepository.save(weather);
    }
}
