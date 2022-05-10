package com.example.testtaskinf.service.impl;

import com.example.testtaskinf.domain.Weather;
import com.example.testtaskinf.domain.positionstack.PositionStackResponse;
import com.example.testtaskinf.domain.positionstack.StackData;
import com.example.testtaskinf.repository.WeatherRepository;
import com.example.testtaskinf.service.ExternalWeatherService;
import com.example.testtaskinf.service.PositionStackService;
import com.example.testtaskinf.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private final WeatherRepository weatherRepository;
    private final List<ExternalWeatherService> externalWeatherServices;
    private final PositionStackService positionStackService;

    @Override
    public void save(Weather weather) {
        weatherRepository.save(weather);
    }

    public void aggregateWeather() {
        Map<String, PositionStackResponse> citiesMap = positionStackService.getCitiesMap();
        citiesMap.forEach((key, value) -> {
            List<Double> temperatures = new ArrayList<>();
            StackData stackData = value.getData().get(0);
            externalWeatherServices.parallelStream().forEach(service -> {
                double temperature = service.getTemperature(stackData.getLatitude(), stackData.getLongitude());
                temperatures.add(temperature);
            });
            double average = temperatures.stream().mapToDouble(d -> d).average().orElseThrow();
            Weather weather = Weather.builder()
                    .city(stackData.getName())
                    .country(stackData.getCountry())
                    .temperature(average)
                    .receivingTime(LocalDateTime.now())
                    .build();
            save(weather);
        });
    }
}
