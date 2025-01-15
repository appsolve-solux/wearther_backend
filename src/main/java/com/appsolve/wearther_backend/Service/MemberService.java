package com.appsolve.wearther_backend.Service;

import com.appsolve.wearther_backend.Dto.LocationPostRequestDto;
import com.appsolve.wearther_backend.Dto.SignInRequest;
import com.appsolve.wearther_backend.Dto.SignUpRequest;
import com.appsolve.wearther_backend.Entity.MemberEntity;
import com.appsolve.wearther_backend.Entity.RefreshToken;
import com.appsolve.wearther_backend.Repository.MemberTasteRepository;
import com.appsolve.wearther_backend.Repository.RefreshTokenRepository;
import com.appsolve.wearther_backend.apiResponse.exception.CustomException;
import com.appsolve.wearther_backend.apiResponse.exception.ErrorCode;
import com.appsolve.wearther_backend.config.auth.PrincipalDetails;
import com.appsolve.wearther_backend.config.jwt.JwtProvider;
import com.appsolve.wearther_backend.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final MemberTasteRepository memberTasteRepository;
    private final LocationService locationService;
    private final RefreshTokenRepository refreshTokenRepository;

    public int getConstitutionByMemberId(Long memberId) {
        int number = memberRepository.findConstitutionByMemberId(memberId);
        return number;
    }

    @Transactional
    public void updateConstitutionByMemberId(Long memberId, Integer constitution) {
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
    }

    public void deleteMember(Long memberId) {
        if (memberRepository.existsById(memberId)) {
            memberRepository.deleteById(memberId);
        } else {
            throw new CustomException(ErrorCode._BAD_REQUEST);
        }
    }

    public void updatePasswordById(Long memberId, String newPassword) {
        String EncodingNewPassword = passwordEncoder.encode(newPassword);
        memberRepository.updateUserPwByMemberId(memberId, EncodingNewPassword);
    }


    //회원가입 필수 정보저장 메소드
    public Long registerMember(SignUpRequest request) {
        try {
            MemberEntity member = new MemberEntity();
            member.setMemberEmail(request.getEmail());
            member.setLoginId(request.getLoginId());
            member.setConstitution(request.getConstitution());
            member.setUserPw(passwordEncoder.encode(request.getPassword()));
            member = memberRepository.save(member);

            LocationPostRequestDto locationRequest = request.getLocationPostRequestDto();
            System.out.println("LocationInfo" + locationRequest.getLocationInfo());
            locationRequest.setMemberId(member.getMemberId());
            locationRequest.setLocationIndex(0);
            locationService.addLocation(locationRequest);
            return member.getMemberId();

        } catch (DataIntegrityViolationException e) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }
    }

    public boolean isLoginIdDuplicated(String loginId) {
        return memberRepository.existsByLoginId(loginId);
    }

    @Transactional
    //로그인할때 accessToken과 refresh token을 둘 다 발급하고 refreshToken은 db에 저장한다.
    public Map<String, ?> signInMember(SignInRequest request) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(request.getLoginId(), request.getUserPw());
            System.out.println(request.getLoginId());

            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

            //로그인 시 리프레시 토큰도 생성함.
            MemberEntity member = principalDetails.getMember();

            Long memberId = member.getMemberId();
            String accessToken = jwtProvider.createAccessToken(String.valueOf(memberId));
            String refreshToken = jwtProvider.createRefreshToken(String.valueOf(memberId));

            RefreshToken existingToken = refreshTokenRepository.findByMember_MemberId(member.getMemberId());
            if (existingToken != null) {
                existingToken.setRefreshToken(refreshToken); // 기존 토큰 업데이트
            } else {
                RefreshToken newToken = RefreshToken.builder()
                        .member(member)
                        .refreshToken(refreshToken)
                        .build();
                refreshTokenRepository.save(newToken);
            }

            Map<String, Object> authResponseMap = new HashMap<>();
            authResponseMap.put("accessToken", accessToken);
            authResponseMap.put("memberId", memberId);
            authResponseMap.put("refreshToken", refreshToken);
            return authResponseMap;
        } catch (BadCredentialsException e) {
            throw new CustomException(ErrorCode.LOGIN_FAILED);
        }

    }

    public String getNewAcceesToken(String refreshToken) {

        jwtProvider.validateToken(refreshToken);
        Long userIdFromToken = jwtProvider.getUserIdFromToken(refreshToken);
        boolean isMemberExists = memberRepository.existsById(userIdFromToken);
        if (!isMemberExists) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
        boolean isRefreshTokenValid = refreshTokenRepository.existsByMember_MemberIdAndRefreshToken(userIdFromToken, refreshToken);
        if (!isRefreshTokenValid) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
        String newAccessToken = jwtProvider.createAccessToken(String.valueOf(userIdFromToken));
        return newAccessToken;
    }

    }


