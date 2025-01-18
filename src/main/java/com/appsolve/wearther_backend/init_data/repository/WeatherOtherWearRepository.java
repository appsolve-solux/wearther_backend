package com.appsolve.wearther_backend.init_data.repository;

import com.appsolve.wearther_backend.init_data.entity.WeatherOtherWear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeatherOtherWearRepository extends JpaRepository<WeatherOtherWear, Long> {
    List<WeatherOtherWear> findByWeatherId(Long weatherId);

}
