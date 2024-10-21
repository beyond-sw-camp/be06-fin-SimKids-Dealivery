package org.example.auth.global.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.auth.global.adaptor.out.UserServiceClient;
import org.example.auth.global.constants.RedisKeys;
import org.example.auth.global.security.custom.model.dto.CustomOAuth2User;
import org.example.auth.domain.user.model.entity.User;
import org.example.auth.domain.user.repository.UserRepository;
import org.example.auth.global.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final int COOKIE_MAX_AGE = 60 * 60 * 24 * 30;
    private final RedisTemplate<String, Object> redisTemplate;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final UserServiceClient userServiceClient;

    @Value("${domain}")
    private String domain;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String name = URLEncoder.encode(oAuth2User.getName(), StandardCharsets.UTF_8.toString());
        String email = URLEncoder.encode(oAuth2User.getEmail(), StandardCharsets.UTF_8.toString());
        String type = URLEncoder.encode(oAuth2User.getRegistrationId(), StandardCharsets.UTF_8.toString());

        Optional<User> optionalUser = userRepository.findByEmail(oAuth2User.getEmail());
        if (optionalUser.isEmpty()){
            //가입된 적이 없으면 가입 기본정보 리턴
            User user = userServiceClient.getUserByEmail(oAuth2User.getEmail());
            if(user == null) {
                getRedirectStrategy().sendRedirect(request, response, domain + "/login/redirect?" + "isSuccess=" + true + "&isExist=" + false
                        + "&name=" + name + "&email=" + email + "&type=" + type);
            }
            else {
                userRepository.save(user);
                optionalUser = userRepository.findByEmail(user.getEmail());
            }
        }
        //받아온 아이디로 가입된적이 있으면 토큰 발급 후 리다이렉트
        User user = optionalUser.get();
        //oauth 로그인 타입이 일치하지 않으면 실패 (naver로그인 인데 google로 등록된 이메일일 경우)
        if (!user.getType().equals(type)){
            getRedirectStrategy().sendRedirect(request, response, domain + "/login/redirect?"+"isSuccess="+false);
        }else {
            String refreshToken = jwtUtil.createRefreshToken(user.getIdx(), user.getEmail(), user.getRole());
            String accessToken = jwtUtil.createAccessToken(user.getIdx(), user.getEmail(), user.getRole());

            //TODO
            // Redis 트랜잭션 처리

            //RefreshToken 엔티티 저장 (기존값 있으면 삭제)
            try {
                Object existingRefreshToken = redisTemplate.opsForValue().get(RedisKeys.USER_REFRESH_TOKEN.getKey() +user.getEmail());
                if ( existingRefreshToken != null){
                    redisTemplate.delete(RedisKeys.USER_REFRESH_TOKEN.getKey()+user.getEmail());
                }

                redisTemplate.opsForValue().set(RedisKeys.USER_REFRESH_TOKEN.getKey()+user.getEmail(), refreshToken,jwtUtil.getREFRESH_EXPIRE(), TimeUnit.MILLISECONDS);

                createTokenCookies(response, accessToken, refreshToken, "user");
                getRedirectStrategy().sendRedirect(request, response,
                        domain + "/login/redirect?"+"isSuccess="+true+"&isExist="+true+"&role="+user.getRole());
            }catch (Exception e){
                //Todo
                // 500에러 안나오게 응답추가
                log.error("Redis Exec Error");
            }


        }
    }

    private void createTokenCookies(HttpServletResponse response, String accessToken, String refreshToken, String type) {
        Cookie aToken = new Cookie("AToken", accessToken);
        aToken.setPath("/");
        aToken.setHttpOnly(true);
        aToken.setSecure(true);

        Cookie rToken = new Cookie("RToken", refreshToken);
        rToken.setPath("/");
        rToken.setHttpOnly(true);
        rToken.setSecure(true);
        rToken.setMaxAge(COOKIE_MAX_AGE);

        Cookie typeCookie = new Cookie("type", type);
        typeCookie.setPath("/");
        typeCookie.setSecure(true);
        typeCookie.setHttpOnly(true);

        response.addCookie(aToken);
        response.addCookie(rToken);
        response.addCookie(typeCookie);
    }

}
