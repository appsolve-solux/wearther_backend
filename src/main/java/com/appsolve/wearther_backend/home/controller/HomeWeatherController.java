package com.appsolve.wearther_backend.home.controller;

import com.appsolve.wearther_backend.apiResponse.ApiResponse;
import com.appsolve.wearther_backend.home.dto.WeatherResponseDto;
import com.appsolve.wearther_backend.home.service.HomeWeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeWeatherController {
    private HomeWeatherService HomeWeatherService;

    @GetMapping("/weather/{latitude}/{longitude}")
    public ResponseEntity<ApiResponse<WeatherResponseDto>> getCurrentWeather (@PathVariable Double latitude, @PathVariable Double longitude){
        WeatherResponseDto currentWeather = HomeWeatherService.getWeatherValue(latitude, longitude);
        return ApiResponse.success(HttpStatus.OK, currentWeather);
    }
}
