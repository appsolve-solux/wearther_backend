package com.appsolve.wearther_backend.Dto;

import lombok.Data;

@Data
public class LocationIndexUpdateRequestDto {
    private Long memberId;
    private int beforeLocationIndex;
    private int afterLocationIndex;
}