package com.appsolve.wearther_backend.init_data.repository;

import com.appsolve.wearther_backend.init_data.entity.WeatherUpperWear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherUpperWearRepository extends JpaRepository<WeatherUpperWear, Long> {
    List<WeatherUpperWear> findByWeatherId(Long weatherId);

}

