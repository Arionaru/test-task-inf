package com.example.testtaskinf.client;

import com.fasterxml.jackson.databind.JsonNode;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface OpenWeatherMapClient {

    @RequestLine("GET /data/2.5/weather?units=metric&lat={lat}&lon={lon}&appid={appid}")
    @Headers({"Content-Type: application/json"})
    JsonNode getData(@Param String lat, @Param String lon, @Param String appid);
}
