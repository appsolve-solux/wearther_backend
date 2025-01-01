package com.appsolve.wearther_backend.Location.Dto;

import lombok.Data;

@Data
public class LocationIndexUpdateRequestDto {
    private Long userId;
    private int beforeLocationIndex;
    private int afterLocationIndex;
}