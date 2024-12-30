package com.appsolve.wearther_backend.closet.repository;

import com.appsolve.wearther_backend.closet.entity.Closet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClosetRepository extends JpaRepository<Closet, Long> {
}
