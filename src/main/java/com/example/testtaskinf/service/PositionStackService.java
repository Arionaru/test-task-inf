package com.example.testtaskinf.service;

import com.example.testtaskinf.domain.positionstack.PositionStackResponse;

import java.util.Map;

public interface PositionStackService {
    Map<String, PositionStackResponse> getCitiesMap();
}
