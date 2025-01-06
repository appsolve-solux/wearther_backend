package com.appsolve.wearther_backend.config.jwt;

import com.appsolve.wearther_backend.apiResponse.exception.CustomException;
import com.appsolve.wearther_backend.apiResponse.exception.ErrorCode;
import com.appsolve.wearther_backend.config.auth.PrincipalDetails;
import com.appsolve.wearther_backend.domain.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;

//인증 필터이다..!
//스프링 시큐리티의 필터
//로그인 요청해서 username과 password를 전송하면 핉터동작을 한다,
//로그인을 진행하는 필터이다.
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private  final AuthenticationManager authenticationManager;
    private  final JwtProvider jwtProvider;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
            //유저 네임, 패스워드 받아서 정상인지 로그인 시도를 해보기
        //AuthenticationManager 로 로그인 시도를 하게 되면
        //PrincipaDetail 서비스가 실행된다
        //그러면 loadByUserName 이 자동으로 실행된다.
        //principalDetails 가 세션에 담기고 -->권한 관리를 위해서 담는다. 엥 권한 관리 필요없으면 굳이 세션에 안 담아도 된대여 ㅅㅂ
        //JWT 토큰을 만들어서 응답해주면 된다.
        try {
            ObjectMapper om = new ObjectMapper();
            Member member = om.readValue(request.getInputStream(),Member.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(member.getLoginId(),member.getMember_pw());
            return authenticationManager.authenticate(authenticationToken);

        } catch (IOException e) {
            throw new CustomException(ErrorCode.USER_NOT_AUTHORIZED);
        }
    }

    //Hash 암호방식
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
        String jwtToken = jwtProvider.createAccessToken(
                principalDetails.getUsername(),
                principalDetails.getMember().getMember_id() );
        response.addHeader("Authorization", "Bearer "+jwtToken);
//클라이언트쪽으로 JWT 헤더로 전달
    }
}
