package com.example.testtaskinf.client;

import com.example.testtaskinf.domain.positionstack.PositionStackResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface PositionStackClient {

    @RequestLine("GET /v1/forward?access_key={accessKey}&query={query}")
    @Headers({"Content-Type: application/json"})
    PositionStackResponse findCity(@Param("query") String query, @Param("accessKey") String accessKey);
}
