package com.appsolve.wearther_backend.Location.Dto;

import lombok.Data;

@Data
public class LocationPostRequestDto {
    private Long userId;
    private String locationInfo;
    private Integer locationIndex;
}
