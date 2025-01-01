package com.appsolve.wearther_backend.Location.Repository;

import com.appsolve.wearther_backend.Location.Entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
    boolean existsByUserIdAndLocationIndex(Long userId, Integer locationIndex);
    void deleteByUserIdAndLocationIndex(Long userId, Integer locationIndex);

    List<LocationEntity> findLocationsByUserId(Long userId);
    Optional<LocationEntity> findByUserIdAndLocationIndex(Long userId, int locationIndex);

    List<LocationEntity> findLocationGreaterThanDeleteIndex(Long userId, Integer locationIndex);


}
