package com.appsolve.wearther_backend.Repository;

import com.appsolve.wearther_backend.Entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
    boolean existsByMemberIdAndLocationIndex(Long memberId, Integer locationIndex);
    void deleteByMemberIdAndLocationIndex(Long memberId, Integer locationIndex);

    List<LocationEntity> findLocationsByMemberId(Long memberId);
    Optional<LocationEntity> findByMemberIdAndLocationIndex(Long memberId, int locationIndex);

    List<LocationEntity> findByMemberIdAndLocationIndexGreaterThan(Long memberId, Integer locationIndex);
}
