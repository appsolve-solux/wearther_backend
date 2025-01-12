package com.appsolve.wearther_backend.closet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingListDto {
    private Long tasteId;
    private List<ShoppingRecommendDto> shoppingRecommendDtoList;
}
