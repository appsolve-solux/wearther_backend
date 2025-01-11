package com.appsolve.wearther_backend.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class LocationWeatherResponseDto {
    @JsonProperty("locations")
    private List<LocationWeatherInfo> locations;

    @Getter
    @AllArgsConstructor
    public static class LocationWeatherInfo {
        private String locationInfo;
        private int locationIndex;
        private String temperature;
    }
}