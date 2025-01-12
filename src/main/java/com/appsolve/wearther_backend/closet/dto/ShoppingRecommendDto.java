package com.appsolve.wearther_backend.closet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingRecommendDto {
    private String category;
    private Long wear_id;
    private String productName;
    private String productUrl;
    private String mallName;
}