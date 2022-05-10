package com.example.testtaskinf.service.impl;

import com.example.testtaskinf.client.OpenWeatherMapClient;
import com.example.testtaskinf.configuration.ProjectProperties;
import com.example.testtaskinf.service.ExternalWeatherService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenWeatherMapService implements ExternalWeatherService {
    private final OpenWeatherMapClient openWeatherMapClient;
    private final ProjectProperties projectProperties;

    @Override
    public Double getTemperature(String lat, String lon) {
        String appid = projectProperties.getServices().get("openweathermap.api-key");
        try {
            JsonNode data = openWeatherMapClient.getData(lat, lon, appid);
            return data.get("main").get("temp").asDouble();
        } catch (Exception exception) {
            log.error("Не удалось получить значение из OpenWeatherMap");
            return null;
        }
    }
}
