package com.example.testtaskinf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TestTaskInfApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestTaskInfApplication.class, args);
    }

}
