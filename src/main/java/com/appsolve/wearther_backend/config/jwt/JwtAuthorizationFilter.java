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

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;

    public JwtAuthorizationFilter(MemberRepository memberRepository, JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
        this.memberRepository =memberRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = jwtProvider.extractJwtFromHeader(request);
        if (token != null) {
            Long userId = jwtProvider.getUserIdFromToken(token);
            if (userId != null) {
                MemberEntity member = memberRepository.findByMemberId(userId);
                if (member!=null){
                PrincipalDetails principalDetails = new PrincipalDetails(member);
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        principalDetails,
                        null,
                        principalDetails.getAuthorities()

                );
                SecurityContextHolder.getContext().setAuthentication(authentication);}
            }
        }
            chain.doFilter(request, response);
    }
}



