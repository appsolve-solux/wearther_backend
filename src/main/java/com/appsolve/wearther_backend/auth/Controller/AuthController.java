package com.appsolve.wearther_backend.auth.Controller;

import com.appsolve.wearther_backend.Dto.LogoutRequest;
import com.appsolve.wearther_backend.Dto.SignInRequest;
import com.appsolve.wearther_backend.apiResponse.ApiResponse;
import com.appsolve.wearther_backend.auth.Dto.ReissueDto;
import com.appsolve.wearther_backend.auth.Dto.TokenResponse;
import com.appsolve.wearther_backend.auth.Service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody ReissueDto reissueDto){
         String accessToken= authService.getNewToken(reissueDto);
        return ApiResponse.success(HttpStatus.OK, Map.of("accessToken", accessToken ));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout( @RequestBody LogoutRequest logoutRequest) {
        authService.logout(logoutRequest);
        return ApiResponse.success(HttpStatus.OK, "로그아웃 완료");
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SignInRequest request) {
        TokenResponse tokenResponse = authService.signInMember(request);
        return ApiResponse.loginSuccess(HttpStatus.OK, tokenResponse, tokenResponse.getAccessToken());
    }

}
