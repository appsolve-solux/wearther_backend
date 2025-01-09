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
import java.util.HashMap;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration.access}")
    private long accessExpiration;
    @Value("${jwt.expiration.refresh}")
    private long refreshExpiration;

    private  SecretKey key;

    @PostConstruct
    public void init() {
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        key = Keys.hmacShaKeyFor(decodedKey);
    }


    public String createRefreshToken(final String id) {
        HashMap<String, Object> map = new HashMap<>();
        String refreshToken = createToken(map, id, refreshExpiration);
        return refreshToken;
    }

    public String createAccessToken(final String id) {
        HashMap<String, Object> map = new HashMap<>();
        return createToken(map, id, accessExpiration);
    }

    public String createToken(
            Map<String, Object> claims,
                            final  String id,
                              long expiration
    ) {
        return  Jwts
                .builder()
                .setClaims(claims)
                .setSubject(id)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(key, SignatureAlgorithm.HS256)
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
        } catch (ExpiredJwtException e) {
            throw new CustomException(ErrorCode.TOKEN_EXPIRED);
        } catch (UnsupportedJwtException e) {
            throw new CustomException(ErrorCode.UNSUPPORTED_TOKEN);
        } catch (IllegalArgumentException e) {
            throw new CustomException(ErrorCode.EMPTY_CLAIMS);
        }

    }

    public  String extractJwtFromHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            return null;
        }
        return header.replace("Bearer ", "");
    }
    public Long getUserIdFromToken(final String token) {
        String subject = getSubjectFromToken(token);
        return Long.parseLong(subject);
    }

    public String getSubjectFromToken(String token) {
        return getUserAllClaimFromToken(token).getSubject();
    }


    public <T> T getUserClaimFromToken(final String token,String claim , Class<T> clazz) {
     Claims claims = getUserAllClaimFromToken(token);
     return  claims.get(claim,clazz);
    }

    public Claims getUserAllClaimFromToken(final String token) {
        validateToken(token);
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }




}
