package com.appsolve.wearther_backend.closet.service;

import com.appsolve.wearther_backend.Entity.MemberEntity;
import com.appsolve.wearther_backend.Repository.MemberRepository;
import com.appsolve.wearther_backend.Service.AuthService;
import com.appsolve.wearther_backend.Service.MemberService;
import com.appsolve.wearther_backend.Service.TasteService;
import com.appsolve.wearther_backend.apiResponse.exception.CustomException;
import com.appsolve.wearther_backend.closet.ShoppingUrls;
import com.appsolve.wearther_backend.closet.dto.*;
import com.appsolve.wearther_backend.closet.entity.Closet;
import com.appsolve.wearther_backend.closet.entity.ClosetLower;
import com.appsolve.wearther_backend.closet.entity.ClosetOther;
import com.appsolve.wearther_backend.closet.entity.ClosetUpper;
import com.appsolve.wearther_backend.closet.repository.ClosetLowerRepository;
import com.appsolve.wearther_backend.closet.repository.ClosetOtherRepository;
import com.appsolve.wearther_backend.closet.repository.ClosetRepository;
import com.appsolve.wearther_backend.closet.repository.ClosetUpperRepository;
import com.appsolve.wearther_backend.init_data.repository.LowerWearRepository;
import com.appsolve.wearther_backend.init_data.repository.OtherWearRepository;
import com.appsolve.wearther_backend.init_data.repository.UpperWearRepository;
import org.apache.commons.lang3.tuple.Pair;
import com.appsolve.wearther_backend.init_data.entity.LowerWear;
import com.appsolve.wearther_backend.init_data.entity.OtherWear;
import com.appsolve.wearther_backend.init_data.entity.UpperWear;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.appsolve.wearther_backend.apiResponse.exception.ErrorCode.CLOTH_TYPE_NOT_FOUND;

@Service
@Transactional
public class ClosetService {
    private final ClosetUpperRepository closetUpperRepository;
    private final ClosetLowerRepository closetLowerRepository;
    private final ClosetOtherRepository closetOtherRepository;
    private final UpperWearRepository upperWearRepository;
    private final LowerWearRepository lowerWearRepository;
    private final OtherWearRepository otherWearRepository;
    private final ClosetRepository closetRepository;
    private final TasteService tasteService;
    private final AuthService authService;
    private final MemberService memberService;

    public ClosetService(AuthService authService,
                         ClosetUpperRepository closetUpperRepository, MemberRepository memberRepository, ClosetLowerRepository closetLowerRepository,
                         ClosetOtherRepository closetOtherRepository, ClosetRepository closetRepository,
                         UpperWearRepository upperWearRepository, LowerWearRepository lowerWearRepository,
                         OtherWearRepository otherWearRepository, TasteService tasteService, MemberService memberService) {
        this.closetUpperRepository = closetUpperRepository;
        this.closetLowerRepository = closetLowerRepository;
        this.closetOtherRepository = closetOtherRepository;
        this.upperWearRepository = upperWearRepository;
        this.lowerWearRepository = lowerWearRepository;
        this.otherWearRepository = otherWearRepository;
        this.closetRepository = closetRepository;
        this.tasteService = tasteService;
        this.authService = authService;
        this.memberService = memberService;
    }

    public ClosetResponseDto getOwnedClothes(Long memberId) {
        return makeUserOwnClothesList(memberId);
    }

    public ClosetResponseDto makeUserOwnClothesList(Long memberId) {
        List<Long> ownedUppers = getOwnedClothesByType(memberId, "upper");
        List<Long> ownedLowers = getOwnedClothesByType(memberId, "lower");
        List<Long> ownedOthers = getOwnedClothesByType(memberId, "other");

        return new ClosetResponseDto(ownedUppers, ownedLowers, ownedOthers);
    }

    public ClosetResponseDto makeUserNotOwnClothesList(Long memberId, ClosetResponseDto tastesOwns) {
        ClosetResponseDto ownClothes = makeUserOwnClothesList(memberId);

        List<Long> unownedUppers = filterUnownedClothes(ownClothes.getUppers(), tastesOwns.getUppers());
        List<Long> unownedLowers = filterUnownedClothes(ownClothes.getLowers(), tastesOwns.getLowers());
        List<Long> unownedOthers = filterUnownedClothes(ownClothes.getOthers(), tastesOwns.getOthers());

        return new ClosetResponseDto(unownedUppers, unownedLowers, unownedOthers);
    }

    public ClosetResponseDto getClothesByTasteAndNotOwned(Long memberId, Long tasteId) {
        List<Long> tasteUppers = tasteService.getClothesByTasteId(tasteId, "upper");
        List<Long> tasteLowers = tasteService.getClothesByTasteId(tasteId, "lower");
        List<Long> tasteOthers = tasteService.getClothesByTasteId(tasteId, "other");

        ClosetResponseDto tastesOwns = new ClosetResponseDto(tasteUppers, tasteLowers, tasteOthers);
        return makeUserNotOwnClothesList(memberId, tastesOwns);
    }

    public ClosetResponseDto getClothesByNoTasteAndNotOwned(Long memberId) { // 취향이 없는 경우
        List<Long> allUppers = upperWearRepository.findAllIds();
        List<Long> allLowers = lowerWearRepository.findAllIds();
        List<Long> allOthers = otherWearRepository.findAllIds();

        ClosetResponseDto tastesOwns = new ClosetResponseDto(allUppers, allLowers, allOthers);
        return makeUserNotOwnClothesList(memberId, tastesOwns);
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
        ClosetResponseDto clothesByTasteAndNotOwned = getClothesByTasteAndNotOwned(memberId, tasteId);

        List<ShoppingRecommendDto> recommendList = new ArrayList<>();
        recommendList.addAll(convertToRecommendDto(clothesByTasteAndNotOwned.getUppers(), "upper"));
        recommendList.addAll(convertToRecommendDto(clothesByTasteAndNotOwned.getLowers(), "lower"));
        recommendList.addAll(convertToRecommendDto(clothesByTasteAndNotOwned.getOthers(), "other"));

        return ShoppingListDto.builder()
                .tasteId(tasteId)
                .shoppingRecommendDtoList(recommendList)
                .build();
    }

    public ShoppingListDto makeShoppingDtoifUserNoTaste(Long memberId) {
        ClosetResponseDto clothesByNoTasteAndNotOwned = getClothesByNoTasteAndNotOwned(memberId);

        List<ShoppingRecommendDto> recommendList = new ArrayList<>();
        recommendList.addAll(convertToRecommendDto(clothesByNoTasteAndNotOwned.getUppers(), "upper"));
        recommendList.addAll(convertToRecommendDto(clothesByNoTasteAndNotOwned.getLowers(), "lower"));
        recommendList.addAll(convertToRecommendDto(clothesByNoTasteAndNotOwned.getOthers(), "other"));

        return ShoppingListDto.builder()
                .tasteId(0L)
                .shoppingRecommendDtoList(recommendList)
                .build();
    }

    private String getProductName(Long clothId, String category) {
        return switch (category) {
            case "upper" -> upperWearRepository.findNameById(clothId);
            case "lower" -> lowerWearRepository.findNameById(clothId);
            case "other" -> otherWearRepository.findNameById(clothId);
            default -> throw new CustomException(CLOTH_TYPE_NOT_FOUND);
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
            default -> throw new CustomException(CLOTH_TYPE_NOT_FOUND);
        };
    }

    public void updateUserCloset(Long memberId, List<Long> newUppers, List<Long> newLowers, List<Long> newOthers) {
        Closet closet = closetRepository.findClosetById(memberId)
                .orElseThrow(() -> new RuntimeException("Closet not found"));

        closet.getClosetUppers().clear();
        closet.getClosetLowers().clear();
        closet.getClosetOthers().clear();

        addNewClothes(closet, newUppers, newLowers, newOthers);

        closetRepository.save(closet);
    }


    private void addNewClothes(Closet closet, List<Long> newUppers, List<Long> newLowers, List<Long> newOthers) {
        for (Long upperId : newUppers) {
            closet.getClosetUppers().add(new ClosetUpper(null, closet, findUpperWearById(upperId)));
        }

        for (Long lowerId : newLowers) {
            closet.getClosetLowers().add(new ClosetLower(null, closet, findLowerWearById(lowerId)));
        }

        for (Long otherId : newOthers) {
            closet.getClosetOthers().add(new ClosetOther(null, closet, findOtherWearById(otherId)));
        }
    }


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

    public void createCloset(ClosetUpdateRequestDto updateRequestDto,String token) {
        MemberEntity member = authService.getMemberEntityFromToken(token);
        Closet closet = Closet.createClosetByMember(member);
        Closet save = closetRepository.save(closet);
        member.setCloset(save);

        addNewClothes(save, updateRequestDto.getUppers(), updateRequestDto.getLowers(), updateRequestDto.getOthers());
        closetRepository.save(closet);
    }
}