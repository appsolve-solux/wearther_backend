package com.appsolve.wearther_backend.Controller;

import com.appsolve.wearther_backend.Dto.SignUpRequest;
import com.appsolve.wearther_backend.apiResponse.ApiResponse;
import com.appsolve.wearther_backend.Dto.SignInRequest;

import com.appsolve.wearther_backend.Service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;


    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest request) {
        Long memberId = memberService.registerMember(request);
        return ApiResponse.success(HttpStatus.
                CREATED,Map.of("memberId", memberId));
    }


    @GetMapping("/duplication-check")
    public  ResponseEntity<?> checkDuplication(@RequestParam String loginId) {
        boolean isDuplicated = memberService.isLoginIdDuplicated(loginId);
        return ApiResponse.success(HttpStatus.OK, Map.of("isDuplicated", isDuplicated));
    }

    @GetMapping("/constitution/{memberId}")
    public ResponseEntity<ApiResponse<Integer>> getConstitution(@PathVariable Long memberId) {
        int constitution = memberService.getConstitutionByMemberId(memberId);
        return ApiResponse.success(HttpStatus.OK, constitution);
    }

    @PatchMapping("/constitution/update/{memberId}/{constitution}")
    public ResponseEntity<ApiResponse<Integer>> updateMemberConstitution(@PathVariable Long memberId, @PathVariable Integer constitution) {
        memberService.updateConstitutionByMemberId(memberId, constitution);
        return ApiResponse.success(HttpStatus.OK, constitution);
    }

    @DeleteMapping("/delete/{memberId}")
    public ResponseEntity<ApiResponse<Long>> deleteMember(@PathVariable Long memberId){
        memberService.deleteMember(memberId);
        return ApiResponse.success(HttpStatus.OK, memberId);
    }

    @PatchMapping("/password/update/{memberId}/{newPassword}")
    public ResponseEntity<ApiResponse<String>> updateMemberPassword(@PathVariable Long memberId, @PathVariable String newPassword){
        memberService.updatePasswordById(memberId, newPassword);
        return ApiResponse.success(HttpStatus.OK, newPassword);
    }



}
