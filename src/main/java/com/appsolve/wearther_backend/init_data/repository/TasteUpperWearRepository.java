package com.appsolve.wearther_backend.init_data.repository;

import com.appsolve.wearther_backend.init_data.entity.TasteUpperWear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasteUpperWearRepository extends JpaRepository<TasteUpperWear, Long> {
}
