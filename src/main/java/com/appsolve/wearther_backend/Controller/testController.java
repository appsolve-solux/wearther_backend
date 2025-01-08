package com.appsolve.wearther_backend.Controller;

import com.appsolve.wearther_backend.config.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
public class testController {
    private final JwtProvider jwtProvider;

    @GetMapping("/api/test/token")
    public ResponseEntity<String> checkToken(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Authorization 헤더가 없거나 잘못되었습니다.");
        }
        try {
            // 헤더에서 토큰 추출
            String token = authorizationHeader.replace("Bearer ", "");

            // 토큰에서 userId(subject) 추출
            Long userId = jwtProvider.getUserIdFromToken(token);

            return ResponseEntity.ok("유효한 토큰입니다. User ID: " + userId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("토큰이 유효하지 않습니다. 에러 메시지: " + e.getMessage());
        }
    }
}

