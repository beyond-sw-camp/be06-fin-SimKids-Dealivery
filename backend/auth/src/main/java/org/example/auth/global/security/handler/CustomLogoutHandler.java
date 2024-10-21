package org.example.auth.global.security.handler;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.auth.global.security.jwt.JwtUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

    private final RedisTemplate<String, Object> redisTemplate;
    private final JwtUtil jwtUtil;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 쿠키에서 값 가져오기
        String refreshToken = getCookieValue(request, "RToken");

        if (refreshToken != null) {
            // 쿠키 값을 사용해 Redis에서 해당 키 삭제
            String email = jwtUtil.getEmail(refreshToken);
            String redisKey = "refresh_token:user:" + email;
            redisTemplate.delete(redisKey);
        }

    }

    // 쿠키에서 특정 쿠키 값 가져오기
    private String getCookieValue(HttpServletRequest request, String cookieName) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
