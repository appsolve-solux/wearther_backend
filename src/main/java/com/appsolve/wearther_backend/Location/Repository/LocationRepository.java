package com.appsolve.wearther_backend.Location.Repository;

import com.appsolve.wearther_backend.Location.Entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
}
