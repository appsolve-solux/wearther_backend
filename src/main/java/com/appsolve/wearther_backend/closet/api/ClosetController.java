package com.appsolve.wearther_backend.closet.api;

import com.appsolve.wearther_backend.Service.MemberService;
import com.appsolve.wearther_backend.apiResponse.ApiResponse;
import com.appsolve.wearther_backend.apiResponse.exception.CustomException;
import com.appsolve.wearther_backend.auth.Service.AuthService;
import com.appsolve.wearther_backend.closet.dto.ClosetResponseDto;
import com.appsolve.wearther_backend.closet.dto.ClosetUpdateRequestDto;
import com.appsolve.wearther_backend.closet.dto.ShoppingListDto;

import com.appsolve.wearther_backend.closet.service.ClosetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.appsolve.wearther_backend.apiResponse.exception.ErrorCode.CLOTH_NOT_FOUND;
import static com.appsolve.wearther_backend.apiResponse.exception.ErrorCode.TASTE_NOT_FOUND;


@Slf4j
@RestController
@RequestMapping("/closet")
public class ClosetController {
    private final ClosetService closetService;
    private final MemberService memberService;
    private final AuthService authService;

    public ClosetController(ClosetService closetService, MemberService memberService, AuthService authService) {
        this.closetService = closetService;
        this.memberService = memberService;
        this.authService = authService;
    }

    @GetMapping("/clothes")
    public ResponseEntity<?> getMemberCloset() {
        Long memberId = authService.extractMemberIdFromContext();

        ClosetResponseDto closetResponseDto = closetService.getOwnedClothes(memberId);
        return ApiResponse.success(HttpStatus.OK, closetResponseDto);
    }

    @GetMapping("/recommend")
    public ResponseEntity<?> getRecommendedCloset() {
        Long memberId = authService.extractMemberIdFromContext();
        ClosetResponseDto recommendedCloset = getRecommendedClosetData(memberId);
        return ApiResponse.success(HttpStatus.OK, recommendedCloset);
    }

    private ClosetResponseDto getRecommendedClosetData(Long memberId) {
        List<Long> tasteIds = memberService.getMemberTastes(memberId);

        Set<Long> uppers = new HashSet<>();
        Set<Long> lowers = new HashSet<>();
        Set<Long> others = new HashSet<>();

        if (tasteIds.isEmpty()) {
            ClosetResponseDto recommendedClothes = closetService.getClothesByNoTasteAndNotOwned(memberId);
            if (recommendedClothes == null) {
                throw new CustomException(CLOTH_NOT_FOUND);
            }
            uppers.addAll(recommendedClothes.getUppers());
            lowers.addAll(recommendedClothes.getLowers());
            others.addAll(recommendedClothes.getOthers());
        } else {
            for (Long tasteId : tasteIds) {
                ClosetResponseDto recommendedClothes = closetService.getClothesByTasteAndNotOwned(memberId, tasteId);
                if (recommendedClothes == null) {
                    throw new CustomException(CLOTH_NOT_FOUND);
                }
                uppers.addAll(recommendedClothes.getUppers());
                lowers.addAll(recommendedClothes.getLowers());
                others.addAll(recommendedClothes.getOthers());
            }
        }
        return new ClosetResponseDto(new ArrayList<>(uppers), new ArrayList<>(lowers), new ArrayList<>(others));
    }

    @GetMapping("/shopping")
    public ResponseEntity<?> getShoppingList() {
        Long memberId = authService.extractMemberIdFromContext();
        List<Long> tasteIds = memberService.getMemberTastes(memberId);

        List<ShoppingListDto> shoppingListDtos = new ArrayList<>();

        if (tasteIds.isEmpty()) {
            ShoppingListDto shoppingListDto = closetService.makeShoppingDtoifUserNoTaste(memberId);
            shoppingListDtos.add(shoppingListDto);
        }
        else {
            try {
                for (Long tasteId : tasteIds) {
                    ShoppingListDto shoppingListDto = closetService.makeShoppingDto(memberId, tasteId);

                    if (shoppingListDto == null)
                        throw new IllegalArgumentException("유효하지 않은 tasteId: " + tasteId);

                    shoppingListDtos.add(shoppingListDto);
                }
            } catch (Exception e) {
                throw new CustomException(TASTE_NOT_FOUND);
            }
        }
        return ApiResponse.success(HttpStatus.OK, shoppingListDtos);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateUserCloset(
                                              @RequestBody ClosetUpdateRequestDto updateRequestDto) {

        Long memberId = authService.extractMemberIdFromContext();

        // 사용자가 보유한 옷을 업데이트
        closetService.updateUserCloset(memberId, updateRequestDto.getUppers(), updateRequestDto.getLowers(), updateRequestDto.getOthers());
        return ApiResponse.success(HttpStatus.OK, updateRequestDto);
    }
}
