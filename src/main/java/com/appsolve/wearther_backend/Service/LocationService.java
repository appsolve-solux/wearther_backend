package com.appsolve.wearther_backend.Service;

import com.appsolve.wearther_backend.Dto.LocationPostRequestDto;
import com.appsolve.wearther_backend.Entity.LocationEntity;
import com.appsolve.wearther_backend.Repository.LocationRepository;
import com.appsolve.wearther_backend.apiResponse.exception.CustomException;
import com.appsolve.wearther_backend.apiResponse.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public LocationEntity addLocation(LocationPostRequestDto locationRequest) {

        Optional<LocationEntity> existingLocation = locationRepository.findByMemberIdAndLocationIndex(
                locationRequest.getMemberId(), locationRequest.getLocationIndex()
        );

        if (existingLocation.isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATE_LOCATION);
        }

        LocationEntity location = new LocationEntity();
        location.setMemberId(locationRequest.getMemberId());
        location.setLocationInfo(locationRequest.getLocationInfo());
        location.setLocationIndex(locationRequest.getLocationIndex());

        return locationRepository.save(location);
    }

    @Transactional
    public void deleteLocationByMemberIdAndLocationIndex(Long memberId, Integer locationIndex) {
        if (locationRepository.existsByMemberIdAndLocationIndex(memberId, locationIndex)) {
            // 삭제 수행
            locationRepository.deleteByMemberIdAndLocationIndex(memberId, locationIndex);

            // 이후 인덱스를 가진 레코드들의 인덱스를 업데이트
            List<LocationEntity> locationsToUpdate = locationRepository.findByMemberIdAndLocationIndexGreaterThan(memberId, locationIndex);
            for (LocationEntity location : locationsToUpdate) {
                location.setLocationIndex(location.getLocationIndex() - 1); // 인덱스를 1 감소
            }
            locationRepository.saveAll(locationsToUpdate); // 변경 사항 저장
        } else {
            throw new CustomException(ErrorCode.INDEX_NOT_FOUND);
        }
    }


    @Transactional
    public void updateLocationIndex(Long memberId, int beforeLocationIndex, int afterLocationIndex) {
        //현재 위치를 찾기
        LocationEntity locationToUpdate = locationRepository
                .findByMemberIdAndLocationIndex(memberId, beforeLocationIndex)
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));

        //해당 memberId를 가진 모든 주소를 불러옴
        List<LocationEntity> locations = locationRepository.findLocationsByMemberId(memberId);

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
