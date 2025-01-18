package com.appsolve.wearther_backend.auth.Dto;

import lombok.Data;

@Data
public class ReissueDto {
    private String refreshToken;
    private String deviceId;
}
