package com.appsolve.wearther_backend.Location.Controller;

import com.appsolve.wearther_backend.Location.Dto.LocationIndexUpdateRequestDto;
import com.appsolve.wearther_backend.Location.Dto.LocationPostRequestDto;
import com.appsolve.wearther_backend.Location.Entity.LocationEntity;
import com.appsolve.wearther_backend.Location.Service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping("/post")
    public ResponseEntity<LocationEntity> addLocation(@RequestBody LocationPostRequestDto locationRequest) {
        LocationEntity locationEntity = locationService.addLocation(locationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{userId}/{locationIndex}")
    public ResponseEntity<LocationEntity> deleteLocation(@PathVariable Long userNumber, @PathVariable Integer locationIndex){
        locationService.deleteLocationByUserIdAndLocationIndex(userNumber, locationIndex);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update-index")
    public ResponseEntity<Void> updateLocationIndex(@RequestBody LocationIndexUpdateRequestDto updateRequest) {
        locationService.updateLocationIndex(updateRequest.getUserId(), updateRequest.getBeforeLocationIndex(), updateRequest.getAfterLocationIndex());
        return ResponseEntity.ok().build();
    }
}