package com.appsolve.wearther_backend.init_data.repository;

import com.appsolve.wearther_backend.init_data.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepostory extends JpaRepository<Weather, Long> {
    @Query("SELECT w.name FROM Weather w WHERE w.id = :weatherId")
    String findByWeatherId(Long weatherId);
}
