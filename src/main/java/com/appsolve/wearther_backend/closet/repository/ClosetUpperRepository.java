package com.appsolve.wearther_backend.closet.repository;

import com.appsolve.wearther_backend.closet.entity.ClosetUpper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClosetUpperRepository extends JpaRepository<ClosetUpper, Long> {
}
