package com.example.testtaskinf.service.impl;

import com.example.testtaskinf.client.PositionStackClient;
import com.example.testtaskinf.configuration.ProjectProperties;
import com.example.testtaskinf.domain.positionstack.PositionStackResponse;
import com.example.testtaskinf.service.PositionStackService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Data
@Service
@RequiredArgsConstructor
public class PositionStackServiceImpl implements PositionStackService {
    private final PositionStackClient positionStackClient;
    private final ProjectProperties projectProperties;
    private final ConfigurableApplicationContext context;
    private Map<String, PositionStackResponse> citiesMap;

    @PostConstruct
    private void setCities() {
        String accessKey = projectProperties.getServices().get("positionstack.api-key");
        Map<String, PositionStackResponse> allAnswers = projectProperties.getCities().stream()
                .map(city -> {
                    PositionStackResponse response = positionStackClient.findCity(city, accessKey);
                    return Map.entry(city, response);
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        List<String> notFoundCities = allAnswers.entrySet().stream()
                .filter(entry -> entry.getValue().getData().isEmpty())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        citiesMap = allAnswers.entrySet().stream()
                .filter(entry -> !notFoundCities.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        notFoundCities.forEach(city -> log.error(String.format("Город %s не найден! Данные для него не могут быть получены!", city)));
        if (citiesMap.isEmpty()) {
            log.error("Ни одного города не найдено! Добавьте города в application.yml по пути project.cities");
            System.exit(SpringApplication.exit(context));
        }
    }
}
