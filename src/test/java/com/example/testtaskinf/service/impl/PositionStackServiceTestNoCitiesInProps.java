package com.example.testtaskinf.service.impl;

import com.example.testtaskinf.AbstractTest;
import com.example.testtaskinf.configuration.TestConfig;
import com.example.testtaskinf.exception.NoCitiesFoundException;
import com.example.testtaskinf.service.PositionStackService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(properties = { "project.cities=" })
@ContextConfiguration(classes = TestConfig.class)
public class PositionStackServiceTestNoCitiesInProps extends AbstractTest {
    @Autowired
    PositionStackService positionStackService;

    @Test
    void noCitiesInPropsTest() {
        Assert.assertThrows(NoCitiesFoundException.class, () -> positionStackService.setCities());
    }
}
