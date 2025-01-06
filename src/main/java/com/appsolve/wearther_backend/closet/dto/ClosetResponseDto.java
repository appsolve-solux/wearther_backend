package com.appsolve.wearther_backend.closet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClosetResponseDto {
    private List<Long> uppers;
    private List<Long> lowers;
    private List<Long> others;
}
