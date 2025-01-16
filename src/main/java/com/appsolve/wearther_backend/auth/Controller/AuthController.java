package com.appsolve.wearther_backend.auth.Controller;

import com.appsolve.wearther_backend.Dto.SignInRequest;
import com.appsolve.wearther_backend.Dto.SignUpRequest;
import com.appsolve.wearther_backend.apiResponse.ApiResponse;
import com.appsolve.wearther_backend.auth.Service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RequiredArgsConstructor
@RestController("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(){
        String accessToken = authService.getNewAcceesToken(refreshToken);
        return ApiResponse.success(HttpStatus.OK, Map.of("accessToken",accessToken));
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SignInRequest request, HttpServletResponse response) {
        Map<String, ?> authMap = authService.signInMember(request);
        String accessTokenToken =(String) authMap.remove("accessToken");



        return ApiResponse.loginSuccess(HttpStatus.OK, authMap, accessTokenToken);
    }

}
