package com.appsolve.wearther_backend.auth.Dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
    private Long memberId;
}
