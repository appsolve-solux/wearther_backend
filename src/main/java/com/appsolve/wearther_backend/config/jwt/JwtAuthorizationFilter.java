package com.appsolve.wearther_backend.config.jwt;

import com.appsolve.wearther_backend.Entity.MemberEntity;
import com.appsolve.wearther_backend.config.auth.PrincipalDetails;
import com.appsolve.wearther_backend.Repository.MemberRepository;
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
        String token = jwtProvider.extractJwtFromHeader(request);
        if (token != null) {
            Long userId = jwtProvider.getUserIdFromToken(token);
            if (userId != null) {
                MemberEntity member = memberRepository.findByMemberId(userId);
                PrincipalDetails principalDetails = new PrincipalDetails(member);
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        principalDetails,
                        null,
                        principalDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
                chain.doFilter(request,response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }}



