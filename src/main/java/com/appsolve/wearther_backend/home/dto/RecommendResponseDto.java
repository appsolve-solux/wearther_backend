package com.appsolve.wearther_backend.home.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RecommendResponseDto {
    private List<Long> upper;
    private List<Long> lower;
    private List<Long> other;
    private String weatherInfo;
}
