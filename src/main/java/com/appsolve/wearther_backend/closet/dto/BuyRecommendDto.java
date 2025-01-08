package com.appsolve.wearther_backend.closet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BuyRecommendDto {
    private String category;
    private String productName;
    private String productUrl;
    private String mallName;
}