package com.appsolve.wearther_backend.Service;


import com.appsolve.wearther_backend.config.auth.PrincipalDetails;
import com.appsolve.wearther_backend.config.jwt.JwtProvider;
import com.appsolve.wearther_backend.Domain.Member;
import com.appsolve.wearther_backend.Dto.SignInRequest;
import com.appsolve.wearther_backend.Dto.SignUpRequest;
import com.appsolve.wearther_backend.Repository.MemberRepository;
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
        Member member = Member.builder()
                .login_id(request.getLoginId())
                .member_pw(passwordEncoder.encode(request.getPassword()))
                .member_name(request.getLoginId())
                .member_email(request.getEmail())
                .constitution(request.getConstitution())
                .build();
        memberRepository.save(member);
    }

    public String signInMember(SignInRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getLoginId(), request.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        Long memberId = principalDetails.getMember().getMemberId();
        String jwtToken = jwtProvider.createAccessToken(
                String.valueOf(memberId)
        );
        return jwtToken;
    }
}
