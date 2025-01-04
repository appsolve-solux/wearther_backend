package com.appsolve.wearther_backend.closet.service;

import com.appsolve.wearther_backend.Service.TasteService;
import com.appsolve.wearther_backend.closet.dto.ClosetResponseDto;
import com.appsolve.wearther_backend.closet.repository.ClosetLowerRepository;
import com.appsolve.wearther_backend.closet.repository.ClosetOtherRepository;
import com.appsolve.wearther_backend.closet.repository.ClosetUpperRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClosetService {
    private final ClosetUpperRepository closetUpperRepository;
    private final ClosetLowerRepository closetLowerRepository;
    private final ClosetOtherRepository closetOtherRepository;
    private final TasteService tasteService;

    public ClosetService(ClosetUpperRepository closetUpperRepository, ClosetLowerRepository closetLowerRepository,
                         ClosetOtherRepository closetOtherRepository, TasteService tasteService) {
        this.closetUpperRepository = closetUpperRepository;
        this.closetLowerRepository = closetLowerRepository;
        this.closetOtherRepository = closetOtherRepository;
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
}
