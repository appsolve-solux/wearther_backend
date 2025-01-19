package com.appsolve.wearther_backend.Controller;

import com.appsolve.wearther_backend.Dto.LocationIndexUpdateRequestDto;
import com.appsolve.wearther_backend.Dto.LocationPostRequestDto;
import com.appsolve.wearther_backend.Dto.LocationWeatherResponseDto;
import com.appsolve.wearther_backend.Entity.Location;
import com.appsolve.wearther_backend.Entity.MemberEntity;
import com.appsolve.wearther_backend.Service.LocationService;
import com.appsolve.wearther_backend.Service.LocationWeatherService;
import com.appsolve.wearther_backend.apiResponse.ApiResponse;
import com.appsolve.wearther_backend.auth.Service.AuthService;
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

    private final AuthService authService;


    @PostMapping("/post")
    public ResponseEntity<ApiResponse<LocationPostRequestDto>> addLocation(@RequestHeader("Authorization") String token, @RequestBody LocationPostRequestDto locationRequest) {
        MemberEntity member = authService.getMemberEntityFromToken(token);
        Long memberId = member.getMemberId();

        Location location = locationService.addLocation(memberId, locationRequest);
        return ApiResponse.success(HttpStatus.CREATED, locationRequest);
    }

    @DeleteMapping("/delete/{locationIndex}")
    public ResponseEntity<ApiResponse<Integer>> deleteLocation(@RequestHeader("Authorization") String token, @PathVariable Integer locationIndex) {
        MemberEntity member = authService.getMemberEntityFromToken(token);
        Long memberId = member.getMemberId();

        locationService.deleteLocationByMemberIdAndLocationIndex(memberId, locationIndex);
        return ApiResponse.success(HttpStatus.OK, locationIndex);
    }

    @PatchMapping("/update-index")
    public ResponseEntity<ApiResponse<LocationIndexUpdateRequestDto>> updateLocationIndex(@RequestHeader("Authorizatoin") String token, @RequestBody LocationIndexUpdateRequestDto updateRequest) {
        MemberEntity member = authService.getMemberEntityFromToken(token);
        Long memberId = member.getMemberId();

        locationService.updateLocationIndex(memberId, updateRequest.getBeforeLocationIndex(), updateRequest.getAfterLocationIndex());
        return ApiResponse.success(HttpStatus.OK, updateRequest);
    }

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<LocationWeatherResponseDto>> getLocationList(@RequestHeader("Authorization") String token) {
        MemberEntity member = authService.getMemberEntityFromToken(token);
        Long memberId = member.getMemberId();

        LocationWeatherResponseDto dto = locationWeatherService.getLocationsWeather(memberId);
        return ApiResponse.success(HttpStatus.OK, dto);
    }

    @GetMapping("/current-tmp/{latitude}/{longitude}")
    public ResponseEntity<ApiResponse<String>> getCurrentTemp (@PathVariable Double latitude, @PathVariable Double longitude){
        String currentTmp = locationWeatherService.getCurrentTmp(latitude, longitude);
        return ApiResponse.success(HttpStatus.OK, currentTmp);
    }


}