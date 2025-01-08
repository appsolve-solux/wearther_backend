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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final MemberRepository memberRepository;
    private final MemberTasteRepository memberTasteRepository;

    public int getConstitutionByMemberId(Long memberId){
        int number = memberRepository.findConstitutionByMemberId(memberId);
        return number;
    }

    @Transactional
    public void updateConstitutionByMemberId(Long memberId, Integer constitution){
        memberRepository.updateConstitutionByMemberId(memberId, constitution);
    }

    public MemberEntity getMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode._BAD_REQUEST));
    }

    public List<Long> getMemberTastes(Long memberId) {
        return memberTasteRepository.findByMemberMemberId(memberId)
                .stream()
                .map(memberTaste -> memberTaste.getTaste().getId())
                .collect(Collectors.toList());
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

    public void deleteMember(Long memberId){
        if (memberRepository.existsById(memberId)){
            memberRepository.deleteById(memberId);
        } else {
            throw new CustomException(ErrorCode._BAD_REQUEST);
        }
    }

    public void updatePasswordById(Long memberId, String newPassword){
        memberRepository.updateUserPwByMemberId(memberId, newPassword);
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
