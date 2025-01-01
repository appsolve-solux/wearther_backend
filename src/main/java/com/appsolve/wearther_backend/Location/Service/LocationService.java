package com.appsolve.wearther_backend.Location.Service;

import com.appsolve.wearther_backend.Location.Dto.LocationPostRequestDto;
import com.appsolve.wearther_backend.Location.Entity.LocationEntity;
import com.appsolve.wearther_backend.Location.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public LocationEntity addLocation(LocationPostRequestDto locationRequest) {
        LocationEntity location = new LocationEntity();
        location.setUserId(locationRequest.getUserId());
        location.setLocationInfo(locationRequest.getLocationInfo());
        location.setLocationIndex(locationRequest.getLocationIndex());
        return locationRepository.save(location);
    }

    @Transactional
    public void deleteLocationByUserIdAndLocationIndex(Long userId, Integer locationIndex) {
        if (locationRepository.existsByUserIdAndLocationIndex(userId, locationIndex)) {
            // 삭제 수행
            locationRepository.deleteByUserIdAndLocationIndex(userId, locationIndex);

            // 이후 인덱스를 가진 레코드들의 인덱스를 업데이트
            List<LocationEntity> locationsToUpdate = locationRepository.findLocationGreaterThanDeleteIndex(userId, locationIndex);
            for (LocationEntity location : locationsToUpdate) {
                location.setLocationIndex(location.getLocationIndex() - 1);
            }
            locationRepository.saveAll(locationsToUpdate);
        } else {
            throw new IllegalArgumentException("해당 인덱스의 주소가 존재하지 않습니다.");
        }
    }

    @Transactional
    public void updateLocationIndex(Long userId, int beforeLocationIndex, int afterLocationIndex) {
        //현재 위치를 찾기
        LocationEntity locationToUpdate = locationRepository
                .findByUserIdAndLocationIndex(userId, beforeLocationIndex)
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));

        //해당 userId를 가진 모든 주소를 불러옴
        List<LocationEntity> locations = locationRepository.findLocationsByUserId(userId);

        if (beforeLocationIndex != afterLocationIndex) {
            if (beforeLocationIndex < afterLocationIndex) {
                // 뒤로 이동할 경우
                for (int i = 0; i < locations.size(); i++) {
                    LocationEntity loc = locations.get(i);
                    if (loc.getLocationIndex() > beforeLocationIndex && loc.getLocationIndex() <= afterLocationIndex) {
                        loc.setLocationIndex(loc.getLocationIndex() - 1); // 1씩 내려줌
                        locationRepository.save(loc);
                    }
                }
            } else {
                // 앞쪽으로 이동할 경우
                for (int i = 0; i < locations.size(); i++) {
                    LocationEntity loc = locations.get(i);
                    if (loc.getLocationIndex() >= afterLocationIndex && loc.getLocationIndex() < beforeLocationIndex) {
                        loc.setLocationIndex(loc.getLocationIndex() + 1); // 1씩 올려줌
                        locationRepository.save(loc);
                    }
                }
            }

            locationToUpdate.setLocationIndex(afterLocationIndex);
            locationRepository.save(locationToUpdate);
        }
    }
}
