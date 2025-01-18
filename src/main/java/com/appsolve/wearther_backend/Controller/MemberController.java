package com.appsolve.wearther_backend.Controller;

import com.appsolve.wearther_backend.Dto.SignUpRequest;
import com.appsolve.wearther_backend.Entity.MemberEntity;
import com.appsolve.wearther_backend.Service.MemberService;
import com.appsolve.wearther_backend.apiResponse.ApiResponse;
import com.appsolve.wearther_backend.auth.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final AuthService authService;


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

    @GetMapping("/constitution")
    public ResponseEntity<ApiResponse<Integer>> getConstitution(@RequestHeader("Authorization") String token) {
        MemberEntity member = authService.getMemberEntityFromToken(token);
        Long memberId = member.getMemberId();

        int constitution = memberService.getConstitutionByMemberId(memberId);
        return ApiResponse.success(HttpStatus.OK, constitution);
    }

    @PatchMapping("/constitution/update/{constitution}")
    public ResponseEntity<ApiResponse<Integer>> updateMemberConstitution(@RequestHeader("Authorization") String token, @PathVariable Integer constitution) {
        MemberEntity member = authService.getMemberEntityFromToken(token);
        Long memberId = member.getMemberId();

        memberService.updateConstitutionByMemberId(memberId, constitution);
        return ApiResponse.success(HttpStatus.OK, constitution);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<Long>> deleteMember(@RequestHeader("Authorization") String token) {
        MemberEntity member = authService.getMemberEntityFromToken(token);
        Long memberId = member.getMemberId();

        memberService.deleteMember(memberId);
        return ApiResponse.success(HttpStatus.OK, memberId);
    }

    @PatchMapping("/password/update/{newPassword}")
    public ResponseEntity<ApiResponse<String>> updateMemberPassword(@RequestHeader("Authorization") String token, @PathVariable String newPassword) {
        MemberEntity member = authService.getMemberEntityFromToken(token);
        Long memberId = member.getMemberId();

        memberService.updatePasswordById(memberId, newPassword);
        return ApiResponse.success(HttpStatus.OK, newPassword);
    }


}
