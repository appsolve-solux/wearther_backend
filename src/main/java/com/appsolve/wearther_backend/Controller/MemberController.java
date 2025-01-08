package com.appsolve.wearther_backend.Controller;

import com.appsolve.wearther_backend.apiResponse.ApiResponse;
import com.appsolve.wearther_backend.Dto.SignInRequest;
import com.appsolve.wearther_backend.Dto.SignUpRequest;
import com.appsolve.wearther_backend.Service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest request) {
        memberService.registerMember(request);
        return ApiResponse.success(HttpStatus.
                CREATED,"회원가입 성공!");
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<HttpStatus>> login(@RequestBody SignInRequest request) {
        return ApiResponse.loginSuccess(HttpStatus.OK, memberService.signInMember(request));
    }


//    @PostMapping("/{memberid}/tastes")
//    public ResponseEntity<?> addTastes( @RequestBody List<Long> tasteIds) {
//        memberService.registerTaste(tasteIds);
//        return ApiResponse.success(HttpStatus.CREATED,"취향등록 완료")
//    }


    //이메일 중복검사
    //아이디 중복검사

}
