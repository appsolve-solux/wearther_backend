package com.appsolve.wearther_backend.Controller;

import com.appsolve.wearther_backend.Dto.LocationIndexUpdateRequestDto;
import com.appsolve.wearther_backend.Dto.LocationPostRequestDto;
import com.appsolve.wearther_backend.Entity.LocationEntity;
import com.appsolve.wearther_backend.Service.LocationService;
import com.appsolve.wearther_backend.apiResponse.ApiResponse;
import com.appsolve.wearther_backend.apiResponse.exception.CustomException;
import com.appsolve.wearther_backend.apiResponse.exception.ErrorCode;
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
    public ResponseEntity<ApiResponse<LocationEntity>> addLocation(@RequestBody LocationPostRequestDto locationRequest) {
        LocationEntity locationEntity = locationService.addLocation(locationRequest);
        return ApiResponse.success(HttpStatus.CREATED, locationEntity);
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