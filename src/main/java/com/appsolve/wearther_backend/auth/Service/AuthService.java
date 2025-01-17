package com.appsolve.wearther_backend.auth.Service;

import com.appsolve.wearther_backend.Dto.LogoutRequest;
import com.appsolve.wearther_backend.Dto.SignInRequest;
import com.appsolve.wearther_backend.Entity.MemberEntity;
import com.appsolve.wearther_backend.auth.Dto.ReissueDto;
import com.appsolve.wearther_backend.auth.Dto.TokenResponse;
import com.appsolve.wearther_backend.auth.Entity.RefreshToken;
import com.appsolve.wearther_backend.Repository.MemberRepository;
import com.appsolve.wearther_backend.auth.Repository.RefreshTokenRepository;
import com.appsolve.wearther_backend.apiResponse.exception.CustomException;
import com.appsolve.wearther_backend.apiResponse.exception.ErrorCode;
import com.appsolve.wearther_backend.auth.PrincipalDetails;
import com.appsolve.wearther_backend.auth.jwt.JwtProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

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
    public TokenResponse signInMember(SignInRequest request) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(request.getLoginId(), request.getUserPw());
            System.out.println(request.getLoginId());

            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

            MemberEntity member = principalDetails.getMember();

            Long memberId = member.getMemberId();
            System.out.println(memberId);
            String accessToken = jwtProvider.createAccessToken(String.valueOf(memberId));
            System.out.println(accessToken);
            //로그인 시 리프레시 토큰도 생성함.
            String refreshToken = jwtProvider.createRefreshToken(String.valueOf(memberId));

            Optional<RefreshToken> existingRefreshToken = refreshTokenRepository.findByMember_MemberId(member.getMemberId());

            existingRefreshToken.ifPresentOrElse(
                    existingToken -> {
                        existingToken.setRefreshToken(refreshToken); // 기존 토큰 업데이트
                    },
                    () -> {
                        RefreshToken newToken = RefreshToken.builder()
                                .member(member)
                                .refreshToken(refreshToken)
                                .deviceId(request.getDeviceId())
                                .build();
                        refreshTokenRepository.save(newToken);
                    }
            );

            TokenResponse tokenResponse = TokenResponse.builder().refreshToken(refreshToken).accessToken(accessToken).memberId(memberId).build();
            return tokenResponse;
        } catch (BadCredentialsException e) {
            throw new CustomException(ErrorCode.LOGIN_FAILED);
        }

    }

    @Transactional
    public void logout(String accessToken, LogoutRequest logoutRequest) {
        MemberEntity member = getMemberEntityFromToken(accessToken);
        RefreshToken existingToken = (RefreshToken) refreshTokenRepository.findByMember_MemberIdAndRefreshTokenAndDeviceId(member.getMemberId(), logoutRequest.getRefreshToken(), logoutRequest.getDeviceId())
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_TOKEN));
        refreshTokenRepository.delete(existingToken);
    }


    public String getNewToken(ReissueDto reissueDto) {
        String refreshToken = reissueDto.getRefreshToken();
        //1.리프레시 토큰 검증

        jwtProvider.checkRefreshToken(refreshToken);
        //2. 리프레시 토큰에서 아이디 뽑아서 확인 -> 유저 존재하고 리프레시 토큰이 있는 상황이면 (만료가 안 됐다면,로그아웃한 토큰이 아니라면) 액세스반환
        Long userIdFromToken = jwtProvider.getUserIdFromToken(refreshToken);
        boolean isMemberExists = memberRepository.existsById(userIdFromToken);
        if (!isMemberExists) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
        boolean isRefreshTokenValid = refreshTokenRepository.existsByMember_MemberIdAndRefreshToken(userIdFromToken, refreshToken);
        //리프레시 토큰 없는 경우 에러 응답 ->재로그인 요구
        if (!isRefreshTokenValid) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }

        String newAccessToken = jwtProvider.createAccessToken(String.valueOf(userIdFromToken));
        return newAccessToken;
    }
}
