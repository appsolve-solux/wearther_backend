package com.appsolve.wearther_backend.Dto;

import lombok.Data;

@Data
public class LogoutRequest {
    private String refreshToken;
    private String deviceId;
}
