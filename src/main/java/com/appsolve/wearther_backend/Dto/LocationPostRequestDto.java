package com.appsolve.wearther_backend.Dto;

import lombok.Data;

@Data
public class LocationPostRequestDto {
    private String locationInfo;
    private Integer locationIndex;
    private Double latitude;
    private Double longitude;
}
