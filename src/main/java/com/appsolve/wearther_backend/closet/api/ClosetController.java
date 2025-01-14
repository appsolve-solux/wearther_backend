package com.appsolve.wearther_backend.closet.api;

import com.appsolve.wearther_backend.Entity.MemberEntity;
import com.appsolve.wearther_backend.Service.AuthService;
import com.appsolve.wearther_backend.Service.TasteService;
import com.appsolve.wearther_backend.Service.MemberService;
import com.appsolve.wearther_backend.apiResponse.ApiResponse;
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


@Slf4j
@RestController
@RequestMapping("/api/closet")
public class ClosetController {
    private final ClosetService closetService;
    private final MemberService memberService;
    private final AuthService authService;

    public ClosetController(ClosetService closetService, MemberService memberService, TasteService tasteService, AuthService authService) {
        this.closetService = closetService;
        this.memberService = memberService;
        this.authService = authService;
    }

    @PostMapping("createCloset")
    public  ResponseEntity<?> createClosetByMember(@RequestHeader("Authorization") String token, @RequestBody ClosetUpdateRequestDto updateRequestDto) {
        closetService.createCloset(updateRequestDto, token);
        return ApiResponse.success(HttpStatus.CREATED,"옷장 만들었습니다.");
    }

    @GetMapping("/clothes/{memberId}")
    public ResponseEntity<?> getMemberCloset(@RequestHeader("Authorization") String token) {
        MemberEntity member = authService.getMemberEntityFromToken(token);
        Long memberId = member.getMemberId();
        ClosetResponseDto closetResponseDto = closetService.getOwnedClothes(memberId);
        return ApiResponse.success(HttpStatus.OK, closetResponseDto);
    }

    @GetMapping("/recommend/{memberId}")
    public ResponseEntity<?> getRecommendedCloset(@RequestHeader("Authorization") String token) {
        MemberEntity member = authService.getMemberEntityFromToken(token);
        Long memberId = member.getMemberId();
        ClosetResponseDto recommendedCloset = getRecommendedClosetData(memberId);
        return ApiResponse.success(HttpStatus.OK, recommendedCloset);
    }

    private ClosetResponseDto getRecommendedClosetData(Long memberId) {
        List<Long> tasteIds = memberService.getMemberTastes(memberId);

        Set<Long> uppers = new HashSet<>();
        Set<Long> lowers = new HashSet<>();
        Set<Long> others = new HashSet<>();

        for (Long tasteId : tasteIds) {
            ClosetResponseDto recommendedClothes = closetService.getClothesByTasteAndNotOwned(memberId, tasteId);
            uppers.addAll(recommendedClothes.getUppers());
            lowers.addAll(recommendedClothes.getLowers());
            others.addAll(recommendedClothes.getOthers());
        }

        return new ClosetResponseDto(new ArrayList<>(uppers), new ArrayList<>(lowers), new ArrayList<>(others));
    }

    @GetMapping("/shopping/{memberId}")
    public ResponseEntity<?> getShoppingList(@RequestHeader("Authorization") String token) {
        MemberEntity member = authService.getMemberEntityFromToken(token);
        Long memberId = member.getMemberId();
        List<Long> tasteIds = memberService.getMemberTastes(memberId);

        List<ShoppingListDto> shoppingListDtos = new ArrayList<>();

        if (tasteIds.isEmpty()) {
            ShoppingListDto shoppingListDto = closetService.makeShoppingDtoifUserNoTaste(memberId);
            shoppingListDtos.add(shoppingListDto);
        }
        else {
            for (Long tasteId : tasteIds) {
                System.out.println(tasteId);
                ShoppingListDto shoppingListDto = closetService.makeShoppingDto(memberId, tasteId);
                shoppingListDtos.add(shoppingListDto);
            }
        }
        return ApiResponse.success(HttpStatus.OK, shoppingListDtos);
    }

    @PatchMapping("/update/{memberId}")
    public ResponseEntity<?> updateUserCloset(@PathVariable("memberId") Long memberId,
                                              @RequestBody ClosetUpdateRequestDto updateRequestDto) {
        // 사용자가 보유한 옷을 업데이트
        closetService.updateUserCloset(memberId, updateRequestDto.getUppers(), updateRequestDto.getLowers(), updateRequestDto.getOthers());
        return ApiResponse.success(HttpStatus.OK, updateRequestDto);
    }


}
