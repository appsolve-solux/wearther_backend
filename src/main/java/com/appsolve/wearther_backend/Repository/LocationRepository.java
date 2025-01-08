package com.appsolve.wearther_backend.Repository;

import com.appsolve.wearther_backend.Entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    boolean existsByMemberIdAndLocationIndex(Long memberId, Integer locationIndex);
    void deleteByMemberIdAndLocationIndex(Long memberId, Integer locationIndex);

    List<Location> findLocationsByMemberId(Long memberId);
    Optional<Location> findByMemberIdAndLocationIndex(Long memberId, int locationIndex);

    List<Location> findByMemberIdAndLocationIndexGreaterThan(Long memberId, Integer locationIndex);
}
