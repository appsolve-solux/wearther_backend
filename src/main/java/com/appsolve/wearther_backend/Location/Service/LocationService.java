package com.appsolve.wearther_backend.Location.Service;

import com.appsolve.wearther_backend.Location.Dto.LocationPostRequestDto;
import com.appsolve.wearther_backend.Location.Entity.LocationEntity;
import com.appsolve.wearther_backend.Location.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public LocationEntity addLocation(LocationPostRequestDto locationRequest) {
        LocationEntity location = new LocationEntity();
        location.setLocationId(locationRequest.getLocationId());
        location.setUserNumber(locationRequest.getUserNumber());
        location.setLocationInfo(locationRequest.getLocationInfo());
        location.setLocationIndex(locationRequest.getLocationIndex());
        return locationRepository.save(location);
    }
}
