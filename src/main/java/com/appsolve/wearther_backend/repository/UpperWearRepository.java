package com.appsolve.wearther_backend.repository;

import com.appsolve.wearther_backend.entity.UpperWear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpperWearRepository extends JpaRepository<UpperWear, Long> {
}