package com.appsolve.wearther_backend.init_data.repository;

import com.appsolve.wearther_backend.init_data.entity.UpperWear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UpperWearRepository extends JpaRepository<UpperWear, Long> {
    @Query("SELECT u.name FROM UpperWear u WHERE u.id = :id")
    String findNameById(Long id);
}