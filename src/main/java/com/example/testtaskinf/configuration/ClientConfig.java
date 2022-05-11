package com.example.testtaskinf.configuration;

import com.example.testtaskinf.client.OpenWeatherMapClient;
import com.example.testtaskinf.client.PositionStackClient;
import com.example.testtaskinf.client.TomorrowIoClient;
import com.example.testtaskinf.client.WeatherApiClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.Feign;
import feign.Logger;
import feign.httpclient.ApacheHttpClient;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ClientConfig {

    private final ProjectProperties projectProperties;

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }

    @Bean
    public PositionStackClient positionStackClient() {
        String url = projectProperties.getServices().get("positionstack.url");
        return Feign.builder()
                .client(new ApacheHttpClient())
                .encoder(new JacksonEncoder(objectMapper()))
                .decoder(new JacksonDecoder(objectMapper()))
                .logger(new Slf4jLogger(PositionStackClient.class))
                .logLevel(Logger.Level.FULL)
                .target(PositionStackClient.class, url);
    }

    @Bean
    public OpenWeatherMapClient openWeatherMapClient() {
        String url = projectProperties.getServices().get("openweathermap.url");
        return Feign.builder()
                .client(new ApacheHttpClient())
                .encoder(new JacksonEncoder(objectMapper()))
                .decoder(new JacksonDecoder(objectMapper()))
                .logger(new Slf4jLogger(OpenWeatherMapClient.class))
                .logLevel(Logger.Level.FULL)
                .target(OpenWeatherMapClient.class, url);
    }

    @Bean
    public TomorrowIoClient tomorrowIoClient() {
        String url = projectProperties.getServices().get("tommorow-io.url");
        return Feign.builder()
                .client(new ApacheHttpClient())
                .encoder(new JacksonEncoder(objectMapper()))
                .decoder(new JacksonDecoder(objectMapper()))
                .logger(new Slf4jLogger(TomorrowIoClient.class))
                .logLevel(Logger.Level.FULL)
                .target(TomorrowIoClient.class, url);
    }

    @Bean
    public WeatherApiClient weatherApiClient() {
        String url = projectProperties.getServices().get("weather-api.url");
        return Feign.builder()
                .client(new ApacheHttpClient())
                .encoder(new JacksonEncoder(objectMapper()))
                .decoder(new JacksonDecoder(objectMapper()))
                .logger(new Slf4jLogger(WeatherApiClient.class))
                .logLevel(Logger.Level.FULL)
                .target(WeatherApiClient.class, url);
    }

}
