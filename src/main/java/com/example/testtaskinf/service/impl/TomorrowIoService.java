package com.example.testtaskinf.service.impl;

import com.example.testtaskinf.client.TomorrowIoClient;
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
public class TomorrowIoService implements ExternalWeatherService {
    private final TomorrowIoClient tomorrowIoClient;
    private final ProjectProperties projectProperties;

    @Override
    public Double getTemperature(String lat, String lon) {
        String appid = projectProperties.getServices().get("tommorow-io.api-key");
        String location = String.join(", ", List.of(lat, lon));
        JsonNode data = tomorrowIoClient.getData(location, appid);
        try {
            return data.get("data").get("timelines").get(0).get("intervals").get(0).get("values").get("temperature").asDouble();
        } catch (Exception exception) {
            log.error("Не удалось получить значение из Tomorrow-io");
            return null;
        }
    }
}
