package com.appsolve.wearther_backend.config.jwt;

import com.appsolve.wearther_backend.apiResponse.exception.CustomException;
import com.appsolve.wearther_backend.apiResponse.exception.ErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtProviderTest {
    @Autowired
    private JwtProvider jwtProvider;




    @Test
    void init() {
    }

    @Test
    void createRefreshToken() {
    }

    @Test
    void createAccessToken() {
        String userId = "12345";
        String token = jwtProvider.createAccessToken(userId);
        assertNotNull(token, "Access Token 생성 실패");
        assertDoesNotThrow(() -> jwtProvider.validateToken(token), "유효한 토큰 검증 실패");
        assertEquals(userId, jwtProvider.getSubjectFromToken(token), "Subject가 일치하지 않음");
        System.out.println("액세스 토큰 만들기");
    }


    @Test
    void validateToken_withValidate() {
        String userId = "12345";
        String token = jwtProvider.createAccessToken(userId);
        assertDoesNotThrow(() -> jwtProvider.validateToken(token));
    }
    @Test
    void validateToken_withExpiredToken() {
        HashMap<String,Object> map = new HashMap<>();
        String expiredToken = jwtProvider.createToken(map, "12345", -1000);
        CustomException exception = assertThrows(CustomException.class, () -> jwtProvider.validateToken(expiredToken));
        assertEquals(ErrorCode.TOKEN_EXPIRED, exception.getErrorCode(), "만료된 토큰 검증 실패");
    }

    @Test
    void getUserIdFromToken() {
        String userId = "12345";
        String token = jwtProvider.createAccessToken(userId);

        Long extractedUserId = jwtProvider.getUserIdFromToken(token);
        assertEquals(Long.parseLong(userId), extractedUserId, "User ID 추출 실패");
    }


    @Test
    void getUserClaimFromToken() {

        String userId = "12345";
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("role", "admin");
        claims.put("email", "user@example.com");

        Claims extractedClaims = jwtProvider.getUserAllClaimFromToken(jwtProvider.createToken(
                    claims,
                    userId,
                    36000
            ));
        assertEquals("admin", extractedClaims.get("role"), "클레임 'role' 값이 일치하지 않음");
        assertEquals("user@example.com", extractedClaims.get("email"), "클레임 'email' 값이 일치하지 않음");
        assertEquals(userId, extractedClaims.getSubject(), "Subject 값이 일치하지 않음");

    }


}