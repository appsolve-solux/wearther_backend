package com.appsolve.wearther_backend.repository;

import com.appsolve.wearther_backend.entity.Taste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasteRepository extends JpaRepository<Taste, Long> {
}