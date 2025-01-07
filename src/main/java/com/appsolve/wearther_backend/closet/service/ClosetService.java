package com.appsolve.wearther_backend.closet.service;

import com.appsolve.wearther_backend.Service.TasteService;
import com.appsolve.wearther_backend.closet.dto.ClosetResponseDto;
import com.appsolve.wearther_backend.closet.entity.Closet;
import com.appsolve.wearther_backend.closet.entity.ClosetLower;
import com.appsolve.wearther_backend.closet.entity.ClosetOther;
import com.appsolve.wearther_backend.closet.entity.ClosetUpper;
import com.appsolve.wearther_backend.closet.repository.ClosetLowerRepository;
import com.appsolve.wearther_backend.closet.repository.ClosetOtherRepository;
import com.appsolve.wearther_backend.closet.repository.ClosetRepository;
import com.appsolve.wearther_backend.closet.repository.ClosetUpperRepository;
import com.appsolve.wearther_backend.init_data.entity.LowerWear;
import com.appsolve.wearther_backend.init_data.entity.OtherWear;
import com.appsolve.wearther_backend.init_data.entity.UpperWear;
import com.appsolve.wearther_backend.init_data.repository.LowerWearRepository;
import com.appsolve.wearther_backend.init_data.repository.OtherWearRepository;
import com.appsolve.wearther_backend.init_data.repository.UpperWearRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClosetService {
    private final ClosetUpperRepository closetUpperRepository;
    private final ClosetLowerRepository closetLowerRepository;
    private final ClosetOtherRepository closetOtherRepository;
    private final ClosetRepository closetRepository;
    private final TasteService tasteService;
    private final UpperWearRepository upperWearRepository;  // UpperWearRepository 추가
    private final LowerWearRepository lowerWearRepository;  // LowerWearRepository 추가
    private final OtherWearRepository otherWearRepository;
    private final EntityManager entityManager;

    public ClosetService(ClosetUpperRepository closetUpperRepository, ClosetLowerRepository closetLowerRepository,
                         ClosetOtherRepository closetOtherRepository, ClosetRepository closetRepository,
                         UpperWearRepository upperWearRepository, LowerWearRepository lowerWearRepository,
                         OtherWearRepository otherWearRepository,TasteService tasteService, EntityManager entityManager) {
        this.closetUpperRepository = closetUpperRepository;
        this.closetLowerRepository = closetLowerRepository;
        this.closetOtherRepository = closetOtherRepository;
        this.upperWearRepository = upperWearRepository;
        this.lowerWearRepository = lowerWearRepository;
        this.otherWearRepository = otherWearRepository;
        this.closetRepository = closetRepository;
        this.tasteService = tasteService;
        this.entityManager = entityManager;
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


    public void updateUserCloset(Long memberId, List<Long> newUppers, List<Long> newLowers, List<Long> newOthers) {
        // 기존 옷장 조회
        Closet closet = closetRepository.findClosetById(memberId)
                .orElseThrow(() -> new RuntimeException("Closet not found"));

        // 기존 옷 데이터를 삭제하고 새로운 목록으로 업데이트 (clear()로 기존 목록 비우기)
        closet.getClosetUppers().clear(); // 기존 UpperWear 삭제
        closet.getClosetLowers().clear(); // 기존 LowerWear 삭제
        closet.getClosetOthers().clear(); // 기존 OtherWear 삭제

        // 새로운 옷을 추가
        addNewClothes(closet, newUppers, newLowers, newOthers);

        // 업데이트된 옷장을 저장
        closetRepository.save(closet);
    }


    private void addNewClothes(Closet closet, List<Long> newUppers, List<Long> newLowers, List<Long> newOthers) {
        // 새로운 UpperWear 추가
        for (Long upperId : newUppers) {
            ClosetUpper closetUpper = new ClosetUpper(null, closet, findUpperWearById(upperId));
            closet.getClosetUppers().add(closetUpper); // 새 UpperWear 추가
        }

        // 새로운 LowerWear 추가
        for (Long lowerId : newLowers) {
            // 중복되지 않도록 확인
            if (closetLowerRepository.findByClosetIdAndLowerWearId(closet.getId(), lowerId).isEmpty()) {
                ClosetLower closetLower = new ClosetLower(null, closet, findLowerWearById(lowerId));
                closet.getClosetLowers().add(closetLower); // 새 LowerWear 추가
            }
        }

        // 새로운 OtherWear 추가
        for (Long otherId : newOthers) {
            // 중복되지 않도록 확인
            if (closetOtherRepository.findByClosetIdAndOtherWearId(closet.getId(), otherId).isEmpty()) {
                ClosetOther closetOther = new ClosetOther(null, closet, findOtherWearById(otherId));
                closet.getClosetOthers().add(closetOther); // 새 OtherWear 추가
            }
        }
    }

    // 각 의류 ID로 의류 엔티티를 찾는 메서드 수정
    private UpperWear findUpperWearById(Long upperId) {
        return upperWearRepository.findById(upperId)
                .orElseThrow(() -> new RuntimeException("Upper Wear not found"));
    }

    private LowerWear findLowerWearById(Long lowerId) {
        return lowerWearRepository.findById(lowerId)
                .orElseThrow(() -> new RuntimeException("Lower Wear not found"));
    }

    private OtherWear findOtherWearById(Long otherId) {
        return otherWearRepository.findById(otherId)
                .orElseThrow(() -> new RuntimeException("Other Wear not found"));
    }
}