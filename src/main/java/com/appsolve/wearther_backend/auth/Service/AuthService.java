package com.appsolve.wearther_backend.auth.Service;

import com.appsolve.wearther_backend.Dto.SignInRequest;
import com.appsolve.wearther_backend.Entity.MemberEntity;
import com.appsolve.wearther_backend.Entity.RefreshToken;
import com.appsolve.wearther_backend.Repository.MemberRepository;
import com.appsolve.wearther_backend.auth.Repository.RefreshTokenRepository;
import com.appsolve.wearther_backend.apiResponse.exception.CustomException;
import com.appsolve.wearther_backend.apiResponse.exception.ErrorCode;
import com.appsolve.wearther_backend.auth.PrincipalDetails;
import com.appsolve.wearther_backend.auth.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenRepository refreshTokenRepository;

    public MemberEntity getMemberEntityFromToken(String token) {
        token = token.replace("Bearer ", "");
        Long memberId = jwtProvider.getUserIdFromToken(token);
        return memberRepository.findByMemberId(memberId);
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
