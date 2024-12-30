package com.appsolve.wearther_backend.closet.repository;

import com.appsolve.wearther_backend.closet.entity.ClosetLower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClosetLowerRepository extends JpaRepository<ClosetLower, Long> {
}
