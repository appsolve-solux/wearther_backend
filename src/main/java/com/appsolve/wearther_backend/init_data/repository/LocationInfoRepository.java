package com.appsolve.wearther_backend.init_data.repository;

import com.appsolve.wearther_backend.init_data.entity.LocationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationInfoRepository extends JpaRepository<LocationInfo, Long> {
    List<LocationInfo> findByGridXAndGridY(String gridX, String gridY);
}
