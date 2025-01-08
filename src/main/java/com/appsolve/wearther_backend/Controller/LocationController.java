package com.appsolve.wearther_backend.Controller;

import com.appsolve.wearther_backend.Dto.LocationIndexUpdateRequestDto;
import com.appsolve.wearther_backend.Dto.LocationPostRequestDto;
import com.appsolve.wearther_backend.Entity.Location;
import com.appsolve.wearther_backend.Service.LocationService;
import com.appsolve.wearther_backend.apiResponse.ApiResponse;
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
    public ResponseEntity<ApiResponse<Location>> addLocation(@RequestBody LocationPostRequestDto locationRequest) {
        Location location = locationService.addLocation(locationRequest);
        return ApiResponse.success(HttpStatus.CREATED, location);
    }

    @DeleteMapping("/delete/{memberId}/{locationIndex}")
    public ResponseEntity<ApiResponse<Integer>> deleteLocation(@PathVariable Long memberId, @PathVariable Integer locationIndex) {
        locationService.deleteLocationByMemberIdAndLocationIndex(memberId, locationIndex);
        return ApiResponse.success(HttpStatus.OK, locationIndex);
    }

    @PatchMapping("/update-index")
    public ResponseEntity<ApiResponse<LocationIndexUpdateRequestDto>> updateLocationIndex(@RequestBody LocationIndexUpdateRequestDto updateRequest) {
        locationService.updateLocationIndex(updateRequest.getMemberId(), updateRequest.getBeforeLocationIndex(), updateRequest.getAfterLocationIndex());
        return ApiResponse.success(HttpStatus.OK, updateRequest);
    }
}