package com.appsolve.wearther_backend.Location.Dto;

import lombok.Data;

@Data
public class LocationPostRequestDto {
    private Long locationId;
    private Long userNumber;
    private String locationInfo;
    private Integer locationIndex;


}
