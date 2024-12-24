package com.appsolve.wearther_backend.Location.Service;

import com.appsolve.wearther_backend.Location.Entity.LocationEntity;
import com.appsolve.wearther_backend.Location.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public LocationEntity addLocation(Long locationId, Long userNumber, String locationInfo, Integer locationIndex) {
        LocationEntity location = new LocationEntity();
        location.setLocationId(locationId);
        location.setUserNumber(userNumber);
        location.setLocationInfo(locationInfo);
        location.setLocationIndex(locationIndex);
        return locationRepository.save(location);
    }
}
