package com.appsolve.wearther_backend.init_data.repository;

import com.appsolve.wearther_backend.init_data.entity.WeatherLowerWear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherLowerWearRepository extends JpaRepository<WeatherLowerWear, Long> {
    List<WeatherLowerWear> findByWeatherId(Long weatherId);

}
