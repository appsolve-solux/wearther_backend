package com.appsolve.wearther_backend.closet.api;

import com.appsolve.wearther_backend.closet.dto.ClosetResponseDto;
import com.appsolve.wearther_backend.closet.service.ClosetService;
import com.appsolve.wearther_backend.member.Member;
import com.appsolve.wearther_backend.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/closet")
public class ClosetController {
    private final ClosetService closetService;
    private final MemberService memberService;

    // TODO : 사용자 로그인 여부 체크 로직 추가 필요
    // TODO : 인증 객체 사용하는 방법으로 멤버 찾아야 함.

    @GetMapping("/clothes/{memberId}")
    public ResponseEntity<?> getMemberCloset(@PathVariable("memberId") Long memberId) {
        Member member = memberService.getMemberById(memberId);
        ClosetResponseDto closetResponseDto = closetService.getClothes(memberId);
        return ResponseEntity.ok(closetResponseDto);
    }
}
