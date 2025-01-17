package com.appsolve.wearther_backend.Dto;

import com.appsolve.wearther_backend.closet.dto.ClosetUpdateRequestDto;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class SignUpRequest {
    private String loginId;
    private String password;
    @Email
    private String email;
    private int constitution;
    private LocationPostRequestDto locationPostRequestDto;

    private ClosetUpdateRequestDto closetUpdateRequestDto;
    private List<Long> tasteIds; // 취향 ID 리스트 추가

}
