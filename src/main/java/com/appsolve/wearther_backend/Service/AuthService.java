package com.appsolve.wearther_backend.Service;

import com.appsolve.wearther_backend.Entity.MemberEntity;
import com.appsolve.wearther_backend.Repository.MemberRepository;
import com.appsolve.wearther_backend.config.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;

    public MemberEntity getMemberEntityFromToken(String token) {
        token = token.replace("Bearer ", "");
        Long memberId = jwtProvider.getUserIdFromToken(token);
        return memberRepository.findByMemberId(memberId);
    }
}
