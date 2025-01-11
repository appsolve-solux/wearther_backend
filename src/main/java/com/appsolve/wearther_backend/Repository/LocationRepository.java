package com.appsolve.wearther_backend.Repository;

import com.appsolve.wearther_backend.Entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Optional<Location> findByMember_MemberIdAndLocationIndex(Long memberId, Integer locationIndex);

    boolean existsByMember_MemberIdAndLocationIndex(Long memberId, Integer locationIndex);

    void deleteByMember_MemberIdAndLocationIndex(Long memberId, Integer locationIndex);

    List<Location> findByMember_MemberIdAndLocationIndexGreaterThan(Long memberId, Integer locationIndex);

    List<Location> findLocationsByMember_MemberId(Long memberId);

    List<Location> findAllByMember_MemberId(Long memberId);
}

