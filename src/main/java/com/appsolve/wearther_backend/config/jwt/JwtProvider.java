package com.appsolve.wearther_backend.config.jwt;

import com.appsolve.wearther_backend.apiResponse.exception.CustomException;
import com.appsolve.wearther_backend.apiResponse.exception.ErrorCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secret;
    private  SecretKey key;

    @PostConstruct
    public void init() {
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        key = Keys.hmacShaKeyFor(decodedKey);
    }

    public String createRefreshToken(String loginId, long expirationTimeMs) {
        return Jwts.builder()
                .setSubject(loginId) // 사용자 식별자
                .setIssuer("Appsolve") // 발급자
                .setIssuedAt(new Date()) // 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeMs)) // 만료 시간
                .signWith(key, SignatureAlgorithm.HS256) // 서명
                .compact();
    }


    public String createAccessToken(String loginId, Long userId) {
        return Jwts.builder()
                .setSubject(loginId) // 사용자 ID
                .claim("LoginId", loginId)
                .claim("UserId", userId)
                .setIssuer("Appsolve") // 발급자
                .setAudience("Member") // 수신자
                .setIssuedAt(new Date()) // 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + 100L *60 * 60 * 10000)) // 만료 시간
                .signWith(key, SignatureAlgorithm.HS256) // 서명
                .compact();
    }

    public void validateToken(final String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
        } catch (SecurityException e) {
            throw new CustomException(ErrorCode.INVALID_SIGNATURE);
        } catch (MalformedJwtException e) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
//        } catch (ExpiredJwtException e) {
//            throw new CustomException(ErrorCode.TOKEN_EXPIRED, "JWT token is expired");
//        } catch (UnsupportedJwtException e) {
//            throw new CustomException(ErrorCode.UNSUPPORTED_TOKEN, "JWT token is unsupported");
//        } catch (IllegalArgumentException e) {
//            throw new CustomException(ErrorCode.EMPTY_CLAIMS, "JWT claims string is empty");
//        }

    }

    public  String extractJwtFromHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            return null;
        }
        System.out.println("여기 실행됏어염1");
        return header.replace("Bearer ", "");
    }
    //서브젝트 꺼내기-> 현재 설정은 로그인아이디 입니다
    public String getSubjectFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key) // Secret Key 사용
                .build()
                .parseClaimsJws(token) // 토큰 검증
                .getBody()
                .getSubject(); // Subject 꺼내기
    }


    public String getUserClaimFromToken(final String token, String claim) {
        validateToken(token);
        System.out.println("여기 실행 됏어염2");
        return Jwts.parserBuilder()
                .setSigningKey(key) // Secret Key 사용
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get(claim, String.class);
    }




}
