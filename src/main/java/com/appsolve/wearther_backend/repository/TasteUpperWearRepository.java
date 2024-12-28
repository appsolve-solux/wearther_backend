package com.appsolve.wearther_backend.repository;

import com.appsolve.wearther_backend.entity.TasteUpperWear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasteUpperWearRepository extends JpaRepository<TasteUpperWear, Long> {
}
