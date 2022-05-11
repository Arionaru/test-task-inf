package com.example.testtaskinf.repository;

import com.example.testtaskinf.domain.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

    Weather findFirstByCityAndCountryOrderByReceivingTimeDesc(String city, String country);

    @Query(nativeQuery = true, value = "" +
            "SELECT * FROM WEATHER WHERE RECEIVING_TIME >= :receivingTime AND country = :country AND city=:city")
    List<Weather> findWeatherByDay(String city, String country, String receivingTime);
}
