package com.appsolve.wearther_backend.Controller;

import com.appsolve.wearther_backend.apiResponse.ApiResponse;
import com.appsolve.wearther_backend.Dto.SignInRequest;
import com.appsolve.wearther_backend.Dto.SignUpRequest;

import com.appsolve.wearther_backend.Service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.appsolve.wearther_backend.apiResponse.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest request) {
        memberService.registerMember(request);
        return ApiResponse.success(HttpStatus.
                CREATED,"회원가입 성공!");
    }

    @GetMapping("/constitution/{memberId}")
    public ResponseEntity<ApiResponse<Integer>> getConstitution(@PathVariable Long memberId) {
        int constitution = memberService.getConstitutionByMemberId(memberId);
        return ApiResponse.success(HttpStatus.OK, constitution);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<HttpStatus>> login(@RequestBody SignInRequest request) {
        return ApiResponse.loginSuccess(HttpStatus.OK, memberService.signInMember(request));
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
