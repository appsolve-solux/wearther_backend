package com.appsolve.wearther_backend.config.auth;

import com.appsolve.wearther_backend.Domain.Member;
import com.appsolve.wearther_backend.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    /*사용자 식별에 로그인 아이디를 사용합니다.*/
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByLoginId(username);
        return new PrincipalDetails(member);
    }
}
