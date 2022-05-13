package com.example.testtaskinf.repository;

import com.example.testtaskinf.domain.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

    Optional<Weather> findFirstByCityAndCountryOrderByReceivingTimeDesc(String city, String country);

    @Query(nativeQuery = true, value = "" +
            "SELECT * FROM weather WHERE receiving_time >= :receivingTime AND country = :country AND city=:city")
    List<Weather> findWeatherByDay(String city, String country, String receivingTime);
}
