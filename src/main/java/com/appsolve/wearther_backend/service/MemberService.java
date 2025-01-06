package com.appsolve.wearther_backend.service;


import com.appsolve.wearther_backend.config.auth.PrincipalDetails;
import com.appsolve.wearther_backend.config.jwt.JwtProvider;
import com.appsolve.wearther_backend.domain.Member;
import com.appsolve.wearther_backend.dto.SignInRequest;
import com.appsolve.wearther_backend.dto.SignUpRequest;
import com.appsolve.wearther_backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;


    public void registerMember(SignUpRequest request) {
        //건너뛰기 -> 들어오지 않더라도 생성이 되어야함.
        Member member = Member.builder()
                .login_id(request.getLoginId())
                .member_pw(passwordEncoder.encode(request.getPassword()))
                .member_name(request.getLoginId())
                .member_email(request.getEmail())
                .constitution(request.getConstitution())
                .build();
        //필수적인 정보 먼저 저장
        memberRepository.save(member);
    }

    public String signInMember(SignInRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getLoginId(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        String jwtToken = jwtProvider.createAccessToken(
                authentication.getName(),
                ((PrincipalDetails) authentication.getPrincipal()).getMember().getMember_id()
        );
        return jwtToken;
    }
}
