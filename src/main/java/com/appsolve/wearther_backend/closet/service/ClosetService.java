package com.appsolve.wearther_backend.closet.service;

import com.appsolve.wearther_backend.Service.TasteService;
import com.appsolve.wearther_backend.closet.ShoppingUrls;
import com.appsolve.wearther_backend.closet.dto.ClosetResponseDto;
import com.appsolve.wearther_backend.closet.dto.ShoppingListDto;
import com.appsolve.wearther_backend.closet.dto.ShoppingRecommendDto;
import com.appsolve.wearther_backend.closet.repository.ClosetLowerRepository;
import com.appsolve.wearther_backend.closet.repository.ClosetOtherRepository;
import com.appsolve.wearther_backend.closet.repository.ClosetUpperRepository;
import com.appsolve.wearther_backend.init_data.repository.LowerWearRepository;
import com.appsolve.wearther_backend.init_data.repository.OtherWearRepository;
import com.appsolve.wearther_backend.init_data.repository.UpperWearRepository;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ClosetService {
    private final ClosetUpperRepository closetUpperRepository;
    private final ClosetLowerRepository closetLowerRepository;
    private final ClosetOtherRepository closetOtherRepository;
    private final UpperWearRepository upperWearRepository;
    private final LowerWearRepository lowerWearRepository;
    private final OtherWearRepository otherWearRepository;
    private final TasteService tasteService;

    public ClosetService(ClosetUpperRepository closetUpperRepository, ClosetLowerRepository closetLowerRepository, ClosetOtherRepository closetOtherRepository, UpperWearRepository upperWearRepository, LowerWearRepository lowerWearRepository, OtherWearRepository otherWearRepository, TasteService tasteService) {
        this.closetUpperRepository = closetUpperRepository;
        this.closetLowerRepository = closetLowerRepository;
        this.closetOtherRepository = closetOtherRepository;
        this.upperWearRepository = upperWearRepository;
        this.lowerWearRepository = lowerWearRepository;
        this.otherWearRepository = otherWearRepository;
        this.tasteService = tasteService;
    }


    public ClosetResponseDto getOwnedClothes(Long memberId) {
        List<Long> ownedUppers = getOwnedClothesByType(memberId, "upper");
        List<Long> ownedLowers = getOwnedClothesByType(memberId, "lower");
        List<Long> ownedOthers = getOwnedClothesByType(memberId, "other");

        return new ClosetResponseDto(ownedUppers, ownedLowers, ownedOthers);
    }

    public ClosetResponseDto getClothesByTasteAndNotOwned(Long memberId, Long tasteId) {
        List<Long> ownedUppers = getOwnedClothesByType(memberId, "upper");
        List<Long> ownedLowers = getOwnedClothesByType(memberId, "lower");
        List<Long> ownedOthers = getOwnedClothesByType(memberId, "other");

        List<Long> tasteUppers = tasteService.getClothesByTasteId(tasteId, "upper");
        List<Long> tasteLowers = tasteService.getClothesByTasteId(tasteId, "lower");
        List<Long> tasteOthers = tasteService.getClothesByTasteId(tasteId, "other");

        List<Long> unownedUppers = filterUnownedClothes(ownedUppers, tasteUppers);
        List<Long> unownedLowers = filterUnownedClothes(ownedLowers, tasteLowers);
        List<Long> unownedOthers = filterUnownedClothes(ownedOthers, tasteOthers);

        return new ClosetResponseDto(unownedUppers, unownedLowers, unownedOthers);
    }

    private List<Long> getOwnedClothesByType(Long memberId, String clothingType) {
        switch (clothingType) {
            case "upper":
                return closetUpperRepository.findByClosetId(memberId)
                        .stream()
                        .map(closetUpper -> closetUpper.getUpperWear().getId())
                        .collect(Collectors.toList());

            case "lower":
                return closetLowerRepository.findByClosetId(memberId)
                        .stream()
                        .map(closetLower -> closetLower.getLowerWear().getId())
                        .collect(Collectors.toList());

            case "other":
                return closetOtherRepository.findByClosetId(memberId)
                        .stream()
                        .map(closetOther -> closetOther.getOtherWear().getId())
                        .collect(Collectors.toList());

            default:
                return Collections.emptyList();
        }
    }

    private List<Long> filterUnownedClothes(List<Long> ownedClothes, List<Long> tasteClothes) {
        return tasteClothes.stream()
                .filter(clothId -> !ownedClothes.contains(clothId))
                .collect(Collectors.toList());
    }

    public ShoppingListDto makeShoppingDto(Long memberId, Long tasteId) {
        List<Long> ownedUppers = getOwnedClothesByType(memberId, "upper");
        List<Long> ownedLowers = getOwnedClothesByType(memberId, "lower");
        List<Long> ownedOthers = getOwnedClothesByType(memberId, "other");

        List<Long> tasteUppers = tasteService.getClothesByTasteId(tasteId, "upper");
        List<Long> tasteLowers = tasteService.getClothesByTasteId(tasteId, "lower");
        List<Long> tasteOthers = tasteService.getClothesByTasteId(tasteId, "other");

        List<Long> unownedUppers = filterUnownedClothes(ownedUppers, tasteUppers);
        List<Long> unownedLowers = filterUnownedClothes(ownedLowers, tasteLowers);
        List<Long> unownedOthers = filterUnownedClothes(ownedOthers, tasteOthers);

        List<ShoppingRecommendDto> recommendList = new ArrayList<>();
        recommendList.addAll(convertToRecommendDto(unownedUppers, "upper"));
        recommendList.addAll(convertToRecommendDto(unownedLowers, "lower"));
        recommendList.addAll(convertToRecommendDto(unownedOthers, "other"));

        return ShoppingListDto.builder()
                .tasteId(tasteId)
                .shoppingRecommendDtoList(recommendList)
                .build();
    }

    private String getProductName(Long clothId, String category) {
        String result = "";
        return switch (category) {
            case "upper" -> upperWearRepository.findNameById(clothId);
            case "lower" -> lowerWearRepository.findNameById(clothId);
            case "other" -> otherWearRepository.findNameById(clothId);
            default -> result; // 예외 던지기
        };
    }

    private List<ShoppingRecommendDto> convertToRecommendDto(List<Long> clothIds, String category) {
        Map<Long, List<Pair<String, String>>> infoMap = getInfoMapByCategory(category);
        return clothIds.stream()
                .flatMap(clothId -> {
                    List<Pair<String, String>> infoList = infoMap.get(clothId);
                    if (infoList == null) return Stream.empty();
                    return infoList.stream().map(info ->
                            new ShoppingRecommendDto(category, clothId, getProductName(clothId, category), info.getLeft(), info.getRight())
                    );
                })
                .collect(Collectors.toList());
    }

    private Map<Long, List<Pair<String, String>>> getInfoMapByCategory(String category) {
        return switch (category) {
            case "upper" -> ShoppingUrls.UPPER_WEAR_INFO;
            case "lower" -> ShoppingUrls.LOWER_WEAR_INFO;
            case "other" -> ShoppingUrls.OTHER_WEAR_INFO;
            default -> Collections.emptyMap();
        };
    }
}