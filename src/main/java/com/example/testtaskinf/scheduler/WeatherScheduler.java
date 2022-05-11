package com.example.testtaskinf.scheduler;

import com.example.testtaskinf.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherScheduler {
    private final WeatherService weatherService;

    @Scheduled(cron = "#{@cronValue}")
    public void run() {
        log.info("Start to aggregate weather");
        weatherService.aggregateWeather();
        log.info("Aggregate finished");
    }
}
