package com.appsolve.wearther_backend.init_data.repository;

import com.appsolve.wearther_backend.init_data.entity.TasteLowerWear;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface TasteLowerWearRepository extends JpaRepository<TasteLowerWear, Long> {
    List<TasteLowerWear> findByTasteId(Long tasteId);
}
