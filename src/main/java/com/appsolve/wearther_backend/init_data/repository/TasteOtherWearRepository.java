package com.appsolve.wearther_backend.init_data.repository;

import com.appsolve.wearther_backend.init_data.entity.TasteOtherWear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TasteOtherWearRepository extends JpaRepository<TasteOtherWear, Long> {
    List<TasteOtherWear> findByTasteId(Long tasteId);
}
