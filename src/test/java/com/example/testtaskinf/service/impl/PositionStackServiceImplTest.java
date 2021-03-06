package com.example.testtaskinf.service.impl;

import com.example.testtaskinf.AbstractTest;
import com.example.testtaskinf.client.PositionStackClient;
import com.example.testtaskinf.domain.positionstack.PositionStackResponse;
import com.example.testtaskinf.domain.positionstack.StackData;
import com.example.testtaskinf.service.PositionStackService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PositionStackServiceImplTest extends AbstractTest {
    @Autowired
    PositionStackService positionStackService;
    @MockBean
    PositionStackClient positionStackClient;

    @Test
    void testSuccessSetUp() {
        PositionStackResponse response = PositionStackResponse.builder()
                .data(List.of(StackData.builder()
                        .longitude("50")
                        .latitude("50")
                        .label("test")
                        .build()))
                .build();
        Mockito.when(positionStackClient.findCity(Mockito.anyString(), Mockito.anyString())).thenReturn(response);
        positionStackService.setCities();
        System.out.println();
        assertThat(positionStackService.getCitiesMap()).hasSize(3);
    }

    @Test
    void moreThanOneCityFound() {
        PositionStackResponse response = PositionStackResponse.builder()
                .data(List.of(
                        StackData.builder().longitude("50").latitude("50").label("testCity").build(),
                        StackData.builder().longitude("51").latitude("51").label("test1").build()
                ))
                .build();
        Mockito.when(positionStackClient.findCity(Mockito.anyString(), Mockito.anyString())).thenReturn(response);
        positionStackService.setCities();
    }
}