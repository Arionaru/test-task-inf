package com.example.testtaskinf.configuration;

import com.example.testtaskinf.client.PositionStackClient;
import com.example.testtaskinf.service.PositionStackService;
import com.example.testtaskinf.service.impl.PositionStackServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {
    @Autowired
    PositionStackClient positionStackClient;
    @Autowired
    ProjectProperties projectProperties;
    @Autowired
    ConfigurableApplicationContext context;
    @Bean
    public PositionStackService positionStackService() {
        return new PositionStackServiceImpl(positionStackClient, projectProperties, context);
    }
}
