package com.appsolve.wearther_backend.Controller;

import com.appsolve.wearther_backend.Dto.LocationWeatherResponseDto;
import com.appsolve.wearther_backend.Dto.LocationIndexUpdateRequestDto;
import com.appsolve.wearther_backend.Dto.LocationPostRequestDto;
import com.appsolve.wearther_backend.Entity.Location;
import com.appsolve.wearther_backend.Service.LocationService;
import com.appsolve.wearther_backend.Service.LocationWeatherService;
import com.appsolve.wearther_backend.apiResponse.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private LocationWeatherService locationWeatherService;


    @PostMapping("/post")
    public ResponseEntity<ApiResponse<LocationPostRequestDto>> addLocation(@RequestBody LocationPostRequestDto locationRequest) {
        Location location = locationService.addLocation(locationRequest);
        return ApiResponse.success(HttpStatus.CREATED, locationRequest);
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

    @GetMapping("/{memberId}")
    public ResponseEntity<ApiResponse<LocationWeatherResponseDto>> getLocationList (@PathVariable Long memberId){
        LocationWeatherResponseDto dto = locationWeatherService.getLocationsWeather(memberId);
        return ApiResponse.success(HttpStatus.OK, dto);
    }

    @GetMapping("/current-tmp/{latitude}/{longitude}")
    public ResponseEntity<ApiResponse<String>> getCurrentTemp (@PathVariable Double latitude, @PathVariable Double longitude){
        String currentTmp = locationWeatherService.getCurrentTmp(latitude, longitude);
        return ApiResponse.success(HttpStatus.OK, currentTmp);
    }


}