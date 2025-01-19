package com.appsolve.wearther_backend.home.controller;

import com.appsolve.wearther_backend.Entity.MemberEntity;
import com.appsolve.wearther_backend.apiResponse.ApiResponse;
import com.appsolve.wearther_backend.auth.Service.AuthService;
import com.appsolve.wearther_backend.home.dto.RecommendResponseDto;
import com.appsolve.wearther_backend.home.dto.WeatherResponseDto;
import com.appsolve.wearther_backend.home.service.HomeWeatherService;
import com.appsolve.wearther_backend.home.service.TodayRecommendService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class HomeController {
    private final HomeWeatherService homeWeatherService;
    private final TodayRecommendService todayRecommendService;
    private final AuthService authService;

    public HomeController(HomeWeatherService homeWeatherService, TodayRecommendService todayRecommendService, AuthService authService) {
        this.homeWeatherService = homeWeatherService;
        this.todayRecommendService = todayRecommendService;
        this.authService = authService;
    }

    @GetMapping("/weather/{latitude}/{longitude}")
    public ResponseEntity<ApiResponse<WeatherResponseDto>> getCurrentWeather (@PathVariable Double latitude, @PathVariable Double longitude){
        WeatherResponseDto currentWeather = homeWeatherService.getWeatherValue(latitude, longitude);
        return ApiResponse.success(HttpStatus.OK, currentWeather);
    }

    @GetMapping("/recommend/{latitude}/{longitude}")
    public ResponseEntity<ApiResponse<RecommendResponseDto>> getTodayRecommend (@RequestHeader("Authorization") String token, @PathVariable Double latitude, @PathVariable Double longitude){
        MemberEntity member =  authService.getMemberEntityFromToken(token);
        Long memberId = member.getMemberId();
        RecommendResponseDto todayRecommend = todayRecommendService.getTodayRecommend(memberId, latitude, longitude);
        return ApiResponse.success(HttpStatus.OK, todayRecommend);
    }
}
