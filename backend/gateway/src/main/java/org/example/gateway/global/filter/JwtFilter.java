package org.example.gateway.global.filter;

import lombok.RequiredArgsConstructor;
import org.example.gateway.global.constants.RedisKeys;
import org.example.gateway.global.security.model.dto.CustomUserDetails;
import org.example.gateway.global.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import java.util.List;

@Component
public class JwtFilter implements WebFilter {
    private final JwtUtil jwtUtil;
    private final RedisTemplate<String, Object> slaveRedisTemplate;

    public JwtFilter(JwtUtil jwtUtil, @Qualifier("masterRedisTemplate") RedisTemplate<String, Object> slaveRedisTemplate) {
        this.jwtUtil = jwtUtil;
        this.slaveRedisTemplate = slaveRedisTemplate;
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, HttpCookie> cookieArray = request.getCookies();

        // 쿠키가 없을 시 필터 통과
        if (cookieArray == null || cookieArray.isEmpty()) {
            return chain.filter(exchange);
        }

        // 쿠키 매핑
        String accessToken = null;
        String refreshToken = null;
        String type = null;

        for (String cookieName : cookieArray.keySet()) {
            List<HttpCookie> cookies = cookieArray.get(cookieName);
            for (HttpCookie cookie : cookies) {
                if (cookie.getName().equals("AToken")) {
                    accessToken = cookie.getValue();
                }
                if (cookie.getName().equals("RToken")) {
                    refreshToken = cookie.getValue();
                }
                if (cookie.getName().equals("type")) {
                    type = cookie.getValue();
                }
            }
        }

        // 액세스 토큰이 없으면 필터 진행
        if (accessToken == null) {
            return chain.filter(exchange);
        }

        // 액세스 토큰 만료 시 처리
        if (jwtUtil.isExpired(accessToken)) {
            // 리프레시 토큰이 없거나 만료되었을 경우
            if (refreshToken == null || jwtUtil.isExpired(refreshToken)) {
                // 쿠키 삭제 처리
                List<String> cookieNames = List.of("AToken", "RToken", "type");
                cookieNames.forEach(cookieName -> {
                    ResponseCookie cookie = ResponseCookie.from(cookieName, null)
                            .path("/")
                            .httpOnly(true)
                            .secure(true)
                            .maxAge(0)
                            .build();
                    exchange.getResponse().addCookie(cookie);
                });
                return chain.filter(exchange);
            }

            // 리프레시 토큰 진위 판별 후 액세스 토큰 재발급
            String email = jwtUtil.getEmail(refreshToken);
            String existingRefreshToken = String.valueOf(
                    type.equals("user")
                            ? slaveRedisTemplate.opsForValue().get(RedisKeys.USER_REFRESH_TOKEN.getKey() + email)
                            : slaveRedisTemplate.opsForValue().get(RedisKeys.COMPANY_REFRESH_TOKEN.getKey() + email)
            );

            if (!existingRefreshToken.equals(refreshToken)) {
                return chain.filter(exchange);
            }

            accessToken = jwtUtil.createAccessToken(jwtUtil.getIdx(refreshToken), jwtUtil.getEmail(refreshToken), jwtUtil.getRole(refreshToken));
            ResponseCookie cookie = ResponseCookie.from("AToken", accessToken)
                    .path("/")
                    .httpOnly(true)
                    .secure(true)
                    .build();
            exchange.getResponse().addCookie(cookie);
        }

        Long userIdx = jwtUtil.getIdx(accessToken);
        String email = jwtUtil.getEmail(accessToken);
        String role = jwtUtil.getRole(accessToken);

        // CustomUserDetails 객체 생성
        CustomUserDetails userDetails = new CustomUserDetails(userIdx, email, role, AuthorityUtils.createAuthorityList(role));

        // Authentication 객체 생성
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        // SecurityContext 설정
        SecurityContext securityContext = new SecurityContextImpl(authentication);

        exchange.getRequest().mutate()
                .header("X-User-Idx", String.valueOf(userIdx))
                .header("X-User-Email", email)
                .header("X-User-Role", role)
                .build();

        return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(securityContext)));
    }
}

