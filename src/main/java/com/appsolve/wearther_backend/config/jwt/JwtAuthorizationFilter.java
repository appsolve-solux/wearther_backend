package com.appsolve.wearther_backend.config.jwt;

import com.appsolve.wearther_backend.apiResponse.exception.CustomException;
import com.appsolve.wearther_backend.apiResponse.exception.ErrorCode;
import com.appsolve.wearther_backend.config.auth.PrincipalDetails;
import com.appsolve.wearther_backend.domain.Member;
import com.appsolve.wearther_backend.repository.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

//시큐리티가 필터를 가지고 ㅇㅆ는데 그  필터중에서 BasicAuthenticationFilter 라는게 있다.
//권한이나 인증이 필요한 특정주소를 요청했을 시에 이 필터를 무조건 타게 되어있다.
//만약에 권한이나 인증이 필요한 주소가 아니라면 이 필터를 타지 않는다
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;

    public JwtAuthorizationFilter(MemberRepository memberRepository, JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
        this.memberRepository =memberRepository;
    }

    //인증이나 권한이 필요한 주소요청이 있을 때 해당 필터를 타게 된다.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
        String accessToken = jwtProvider.extractJwtFromHeader(request);
        if (accessToken == null) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
        String userName = jwtProvider.getUserClaimFromToken(accessToken, "LoginId");
        if (userName != null) {
            Member member = memberRepository.findByLoginId(userName);
            PrincipalDetails principalDetails = new PrincipalDetails(member);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    principalDetails,
                    null,
                    principalDetails.getAuthorities()
            );
            System.out.println("여기까진 되나?" + authentication.getName());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("필터 체인 이후에도 실행됨");
        }}
        catch (Exception e) {
            logger.info("Failed to authorize/authenticate with JWT due to " + e.getMessage());
        }
        chain.doFilter(request, response);
    }
}



