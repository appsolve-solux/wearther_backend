package com.appsolve.wearther_backend.dto;

public class JwtTokenDto {
    //jwt 인증타입 액세스 토큰 요청시 사용되는 인증 유형이다!
    private String grantType;

    private String accessToken;
    private String refreshToken;
}
