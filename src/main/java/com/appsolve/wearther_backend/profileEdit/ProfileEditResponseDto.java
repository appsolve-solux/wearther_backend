package com.appsolve.wearther_backend.profileEdit;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProfileEditResponseDto {
    private String loginId;
    private String userPw;
    private Integer constitution;
    private List<Long> tasteIds;
}
