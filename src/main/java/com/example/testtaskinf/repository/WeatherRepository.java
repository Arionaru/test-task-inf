package com.example.testtaskinf.repository;

import com.example.testtaskinf.domain.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
}
