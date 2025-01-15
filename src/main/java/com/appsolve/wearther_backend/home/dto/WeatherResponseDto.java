package com.appsolve.wearther_backend.home.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class WeatherResponseDto {
    private String temperature;
    private String temperatureMin;
    private String temperatureMax;
    private String humidity;
    private List<String> hourlyTemp;
    private List<String> hourlySky;
}
