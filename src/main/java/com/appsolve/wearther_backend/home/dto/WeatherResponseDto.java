package com.appsolve.wearther_backend.home.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WeatherResponseDto {
    private String temperature;
    private String temperatureMin;
    private String temperatureMax;
    private String humidity;
}
