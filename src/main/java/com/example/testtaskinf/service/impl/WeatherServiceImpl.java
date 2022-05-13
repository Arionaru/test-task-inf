package com.example.testtaskinf.service.impl;

import com.example.testtaskinf.domain.Weather;
import com.example.testtaskinf.domain.positionstack.PositionStackResponse;
import com.example.testtaskinf.domain.positionstack.StackData;
import com.example.testtaskinf.repository.WeatherRepository;
import com.example.testtaskinf.service.ExternalWeatherService;
import com.example.testtaskinf.service.PositionStackService;
import com.example.testtaskinf.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
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
            StackData stackData = value.getData().get(0);
            List<Double> temperatures = externalWeatherServices.parallelStream()
                    .map(service -> service.getTemperature(stackData.getLatitude(), stackData.getLongitude()))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            if (!temperatures.isEmpty()) {
                double average = temperatures.stream().mapToDouble(d -> d).average().orElseThrow();
                Weather weather = Weather.builder()
                        .city(stackData.getName())
                        .country(stackData.getCountry())
                        .temperature(average)
                        .receivingTime(LocalDateTime.now())
                        .build();
                save(weather);
            } else {
                log.error("Не удалось получить ни одного значения температуры");
            }
        });
    }

    @Override
    public Optional<Weather> getWeatherNow(String city, String country) {
        return weatherRepository.findFirstByCityAndCountryOrderByReceivingTimeDesc(city, country);
    }

    @Override
    public List<Weather> getWeatherByDay(String city, String country, LocalDate localDate) {
        return weatherRepository.findWeatherByDay(city, country, localDate.toString());
    }
}
