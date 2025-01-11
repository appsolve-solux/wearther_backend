package com.appsolve.wearther_backend.closet.repository;

import com.appsolve.wearther_backend.closet.entity.ClosetLower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClosetLowerRepository extends JpaRepository<ClosetLower, Long> {
    List<ClosetLower> findByClosetId(Long memberId);

    Optional<ClosetLower> findByClosetIdAndLowerWearId(Long closetId, Long lowerWearId);

}
