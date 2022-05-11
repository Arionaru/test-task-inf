package com.example.testtaskinf.service.impl;

import com.example.testtaskinf.client.WeatherApiClient;
import com.example.testtaskinf.configuration.ProjectProperties;
import com.example.testtaskinf.service.ExternalWeatherService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherApiService implements ExternalWeatherService {
    private final WeatherApiClient weatherApiClient;
    private final ProjectProperties projectProperties;

    @Override
    public Double getTemperature(String lat, String lon) {
        String appid = projectProperties.getServices().get("weather-api.api-key");
        String location = String.join(", ", List.of(lat, lon));
        try {
            JsonNode data = weatherApiClient.getData(location, appid);
            return data.get("current").get("temp_c").doubleValue();
        } catch (Exception exception) {
            log.error("Не удалось получить значение из WeatherApi");
            return null;
        }
    }
}
