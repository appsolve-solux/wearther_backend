package com.appsolve.wearther_backend.Service;

import com.appsolve.wearther_backend.Dto.LocationPostRequestDto;
import com.appsolve.wearther_backend.Entity.Location;
import com.appsolve.wearther_backend.Entity.MemberEntity;
import com.appsolve.wearther_backend.Repository.LocationRepository;
import com.appsolve.wearther_backend.Repository.MemberRepository;
import com.appsolve.wearther_backend.apiResponse.exception.CustomException;
import com.appsolve.wearther_backend.apiResponse.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;
    private final MemberRepository memberRepository;  // MemberEntity를 조회하기 위해 필요

    public Location addLocation(LocationPostRequestDto locationRequest) {
        Optional<Location> existingLocation = locationRepository.findByMember_MemberIdAndLocationIndex(
                locationRequest.getMemberId(), locationRequest.getLocationIndex()
        );

        if (existingLocation.isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATE_LOCATION);
        }

        MemberEntity member = memberRepository.findById(locationRequest.getMemberId())
                .orElseThrow(() -> new CustomException(ErrorCode._BAD_REQUEST));

        Location location = Location.builder()
                .member(member)
                .locationInfo(locationRequest.getLocationInfo())
                .locationIndex(locationRequest.getLocationIndex())
                .latitude(locationRequest.getLatitude())
                .longitude(locationRequest.getLongitude())
                .build();

        return locationRepository.save(location);
    }

    @Transactional
    public void deleteLocationByMemberIdAndLocationIndex(Long memberId, Integer locationIndex) {
        if (locationRepository.existsByMember_MemberIdAndLocationIndex(memberId, locationIndex)) {
            // 삭제 수행
            locationRepository.deleteByMember_MemberIdAndLocationIndex(memberId, locationIndex);

            // 이후 인덱스를 가진 레코드들의 인덱스를 업데이트
            List<Location> locationsToUpdate = locationRepository
                    .findByMember_MemberIdAndLocationIndexGreaterThan(memberId, locationIndex);

            locationsToUpdate.forEach(location -> {
                Location updatedLocation = Location.builder()
                        .locationId(location.getLocationId())
                        .member(location.getMember())
                        .locationInfo(location.getLocationInfo())
                        .locationIndex(location.getLocationIndex() - 1)
                        .latitude(location.getLatitude())
                        .longitude(location.getLongitude())
                        .build();
                locationRepository.save(updatedLocation);
            });
        } else {
            throw new CustomException(ErrorCode.INDEX_NOT_FOUND);
        }
    }

    @Transactional
    public void updateLocationIndex(Long memberId, int beforeLocationIndex, int afterLocationIndex) {
        Location locationToUpdate = locationRepository
                .findByMember_MemberIdAndLocationIndex(memberId, beforeLocationIndex)
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));

        List<Location> locations = locationRepository.findLocationsByMember_MemberId(memberId);

        if (beforeLocationIndex != afterLocationIndex) {
            if (beforeLocationIndex < afterLocationIndex) {
                // 뒤로 이동할 경우
                locations.stream()
                        .filter(loc -> loc.getLocationIndex() > beforeLocationIndex
                                && loc.getLocationIndex() <= afterLocationIndex)
                        .forEach(loc -> {
                            Location updatedLocation = Location.builder()
                                    .locationId(loc.getLocationId())
                                    .member(loc.getMember())
                                    .locationInfo(loc.getLocationInfo())
                                    .locationIndex(loc.getLocationIndex() - 1)
                                    .latitude(loc.getLatitude())
                                    .longitude(loc.getLongitude())
                                    .build();
                            locationRepository.save(updatedLocation);
                        });
            } else {
                // 앞쪽으로 이동할 경우
                locations.stream()
                        .filter(loc -> loc.getLocationIndex() >= afterLocationIndex
                                && loc.getLocationIndex() < beforeLocationIndex)
                        .forEach(loc -> {
                            Location updatedLocation = Location.builder()
                                    .locationId(loc.getLocationId())
                                    .member(loc.getMember())
                                    .locationInfo(loc.getLocationInfo())
                                    .locationIndex(loc.getLocationIndex() + 1)
                                    .latitude(loc.getLatitude())
                                    .longitude(loc.getLongitude())
                                    .build();
                            locationRepository.save(updatedLocation);
                        });
            }

            Location finalUpdate = Location.builder()
                    .locationId(locationToUpdate.getLocationId())
                    .member(locationToUpdate.getMember())
                    .locationInfo(locationToUpdate.getLocationInfo())
                    .locationIndex(afterLocationIndex)
                    .latitude(locationToUpdate.getLatitude())
                    .longitude(locationToUpdate.getLongitude())
                    .build();
            locationRepository.save(finalUpdate);
        }
    }
}