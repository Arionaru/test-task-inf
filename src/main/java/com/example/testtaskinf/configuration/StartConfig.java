package com.example.testtaskinf.configuration;

import com.example.testtaskinf.client.PositionStackClient;
import com.example.testtaskinf.service.PositionStackService;
import com.example.testtaskinf.service.impl.PositionStackServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class StartConfig {
    private final PositionStackClient positionStackClient;
    private final ProjectProperties projectProperties;
    private final ConfigurableApplicationContext context;

    @Bean
    public PositionStackService positionStackService() {
        PositionStackService positionStackService = new PositionStackServiceImpl(positionStackClient, projectProperties);
        positionStackService.setCities();
        return positionStackService;
    }
}
