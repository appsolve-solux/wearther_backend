package com.appsolve.wearther_backend.Location.Controller;

import com.appsolve.wearther_backend.Location.Dto.LocationPostRequestDto;
import com.appsolve.wearther_backend.Location.Entity.LocationEntity;
import com.appsolve.wearther_backend.Location.Service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping("/post")
    public LocationEntity addLocation(@RequestBody LocationPostRequestDto locationRequest) {
        return locationService.addLocation(
                locationRequest.getLocationId(),
                locationRequest.getUserNumber(),
                locationRequest.getLocationInfo(),
                locationRequest.getLocationIndex()
        );
    }
}