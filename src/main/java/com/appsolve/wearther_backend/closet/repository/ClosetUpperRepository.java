package com.appsolve.wearther_backend.closet.repository;

import com.appsolve.wearther_backend.closet.entity.ClosetUpper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClosetUpperRepository extends JpaRepository<ClosetUpper, Long> {
    List<ClosetUpper> findByClosetId(Long memberId);

    Optional<ClosetUpper> findByClosetIdAndUpperWearId(Long closetId, Long upperWearId);

}
