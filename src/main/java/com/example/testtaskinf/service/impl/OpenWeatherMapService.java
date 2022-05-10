package com.example.testtaskinf.service.impl;

import com.example.testtaskinf.client.OpenWeatherMapClient;
import com.example.testtaskinf.configuration.ProjectProperties;
import com.example.testtaskinf.service.ExternalWeatherService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenWeatherMapService implements ExternalWeatherService {
    private final OpenWeatherMapClient openWeatherMapClient;
    private final ProjectProperties projectProperties;

    @Override
    public double getTemperature(String lat, String lon) {
        String appid = projectProperties.getServices().get("openweathermap.api-key");
        JsonNode data = openWeatherMapClient.getData(lat, lon, appid);
        return data.get("main").get("temp").asDouble();
    }
}
