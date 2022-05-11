package com.example.testtaskinf.client;

import com.fasterxml.jackson.databind.JsonNode;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface TomorrowIoClient {

    @RequestLine("GET /v4/timelines?timesteps=current&units=metric&fields=temperature&location={location}&apikey={appid}")
    @Headers({"Content-Type: application/json"})
    JsonNode getData(@Param String location, @Param String appid);
}
