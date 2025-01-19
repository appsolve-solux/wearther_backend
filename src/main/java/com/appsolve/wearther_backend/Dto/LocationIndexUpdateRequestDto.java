package com.appsolve.wearther_backend.Dto;

import lombok.Data;

@Data
public class LocationIndexUpdateRequestDto {
    private int beforeLocationIndex;
    private int afterLocationIndex;
}