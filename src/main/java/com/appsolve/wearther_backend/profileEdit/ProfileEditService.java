package com.appsolve.wearther_backend.profileEdit;


import com.appsolve.wearther_backend.Entity.MemberEntity;
import com.appsolve.wearther_backend.Service.MemberService;
import com.appsolve.wearther_backend.Service.MemberTasteService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileEditService {
    private final MemberService memberService;
    private final MemberTasteService memberTasteService;

    public ProfileEditService(MemberService memberService, MemberTasteService memberTasteService) {
        this.memberService = memberService;
        this.memberTasteService = memberTasteService;
    }

    public ProfileEditResponseDto getProfileByMemberId(Long memberId) {
        MemberEntity member = memberService.getMemberById(memberId);
        List<Long> tasteIds = memberTasteService.getMemberTasteIds(memberId);

        if (tasteIds == null) {
            tasteIds = new ArrayList<>();
        }


        ProfileEditResponseDto dto = new ProfileEditResponseDto(
                member.getLoginId(),
                member.getUserPw(),
                member.getConstitution(),
                tasteIds
        );
        return dto;
    }
}



