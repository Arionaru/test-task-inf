package com.example.testtaskinf.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties(prefix = "project")
public class ProjectProperties {
    private Map<String, String> services;
    private String poolCron;
    private List<String> cities;

}
