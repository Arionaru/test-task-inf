package com.example.testtaskinf;

import com.example.testtaskinf.repository.WeatherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AbstractTest {
    @Autowired
    WeatherRepository weatherRepository;

    @BeforeEach
    void clearDb() {
        weatherRepository.deleteAll();
    }
}
