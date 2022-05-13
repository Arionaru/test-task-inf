package com.example.testtaskinf.controller;

import com.example.testtaskinf.domain.Weather;
import com.example.testtaskinf.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping
    public List<Weather> getWeatherByDay(@RequestParam String city,
                                         @RequestParam String country,
                                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        if (date != null) {
            return weatherService.getWeatherByDay(city, country, date);
        } else {
            Optional<Weather> weatherNow = weatherService.getWeatherNow(city, country);

            return weatherNow.map(List::of).orElse(Collections.emptyList());
        }
    }
}
