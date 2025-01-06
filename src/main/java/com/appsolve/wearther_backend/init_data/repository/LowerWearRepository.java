package com.appsolve.wearther_backend.init_data.repository;

import com.appsolve.wearther_backend.init_data.entity.LowerWear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LowerWearRepository extends JpaRepository<LowerWear, Long> {
    @Query("SELECT u.name FROM LowerWear u WHERE u.id = :id")
    String findNameById(Long id);
}