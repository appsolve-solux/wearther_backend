package com.appsolve.wearther_backend.config;

import com.appsolve.wearther_backend.apiResponse.exception.handler.CustomAccessDeniedHandler;
import com.appsolve.wearther_backend.apiResponse.exception.handler.CustomAuthenticationEntryPointHandler;
import com.appsolve.wearther_backend.apiResponse.exception.handler.JwtExceptionFilter;
import com.appsolve.wearther_backend.auth.jwt.JwtAuthorizationFilter;
import com.appsolve.wearther_backend.auth.jwt.JwtProvider;
import com.appsolve.wearther_backend.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

//시큐리티 활성화 ->스프링 시큐리티 필터가 스프링 필터체인에  등록된다. spring config 가 바로 스프링 시큐리티 필터임.

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig{
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final CustomAuthenticationEntryPointHandler customAuthenticationEntryPointHandler;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final JwtExceptionFilter jwtExceptionFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger", "/swagger-ui.html", "/swagger-ui/**", "/api-docs", "/api-docs/**", "/v3/api-docs/**","/error","member/refresh").permitAll()
                        .requestMatchers("member/signUp", "member/login", "member/duplication-check").permitAll()
                        .requestMatchers("/images/**", "/js/**", "/webjars/**").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 사용 안 함
                .httpBasic(AbstractHttpConfigurer::disable) // HTTP Basic 인증 비활성화
                .formLogin(AbstractHttpConfigurer::disable) // 폼 기반 로그인 비활성
                .addFilterBefore(jwtExceptionFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(jwtAuthorizationFilter, JwtExceptionFilter.class)
                .exceptionHandling(conf -> conf
                .authenticationEntryPoint(customAuthenticationEntryPointHandler)
                .accessDeniedHandler(customAccessDeniedHandler)
        );

        return http.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 인증 정보를 포함한 요청 허용
        config.addAllowedOrigin("*"); // 모든 도메인 허용
        config.addAllowedHeader("*"); // 모든 헤더 허용
        config.addAllowedMethod("*"); // 모든 HTTP 메서드 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // 모든 경로에 CORS 설정 적용
        return source;
    }
}
