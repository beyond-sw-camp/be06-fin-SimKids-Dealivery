package org.example.gateway.global.security;

import lombok.RequiredArgsConstructor;
import org.example.gateway.global.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // CSRF 보호 비활성화
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable) // 폼 로그인 비활성화
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable) // httpBasic 비활성화
                .authorizeExchange(authorize -> authorize
                        .pathMatchers("/api/user/signup","/api/login","/api/logout",
                                "/api/company/signup","/api/company/email/verify","/api/user/email/verify",
                                "/api/user/social/signup","/api/user/user-idx/**",
                                "/api/product-boards/decrease/stock",
                                "/api/product-boards/restore/stock",
                                "/api/product-boards/list", "/api/product-boards/main/list"
                        ,"/api/product-boards/*/detail").permitAll()
//                        .pathMatchers("/api/product-boards/**").authenticated()
//                        .pathMatchers("/api/user/**").hasRole("USER") // /user/** 경로는 ROLE_USER 권한 필요
//                        .pathMatchers("/api/company/**").hasRole("COMPANY")
//                        .pathMatchers("/api/orders/**").authenticated()
                        .anyExchange().permitAll() // 나머지 요청은 인증 필요
                );
        http.addFilterBefore(jwtFilter, SecurityWebFiltersOrder.AUTHENTICATION);

        return http.build();
    }


}