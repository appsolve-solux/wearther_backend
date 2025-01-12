package com.appsolve.wearther_backend.closet.dto;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class ClosetUpdateRequestDto {
    private List<Long> uppers = Collections.emptyList();
    private List<Long> lowers = Collections.emptyList();
    private List<Long> others = Collections.emptyList();

}