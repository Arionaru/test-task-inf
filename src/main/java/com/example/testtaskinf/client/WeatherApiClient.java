package com.example.testtaskinf.client;

import com.fasterxml.jackson.databind.JsonNode;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface WeatherApiClient {
    @RequestLine("GET /v1/current.json?key={appid}&q={location}")
    @Headers({"Content-Type: application/json"})
    JsonNode getData(@Param String location, @Param String appid);
}
