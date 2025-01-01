package com.appsolve.wearther_backend.Dto;

import lombok.Data;

@Data
public class LocationPostRequestDto {
    private Long memberId;
    private String locationInfo;
    private Integer locationIndex;
}
