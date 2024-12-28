package com.appsolve.wearther_backend.repository;

import com.appsolve.wearther_backend.entity.LowerWear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LowerWearRepository extends JpaRepository<LowerWear, Long> {
}