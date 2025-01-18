package com.appsolve.wearther_backend.auth.jwt;

import com.appsolve.wearther_backend.Entity.MemberEntity;
import com.appsolve.wearther_backend.auth.PrincipalDetails;
import com.appsolve.wearther_backend.Repository.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;



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



