package com.appsolve.wearther_backend.closet.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClosetUpdateRequestDto {
    private List<Long> uppers;
    private List<Long> lowers;
    private List<Long> others;

}