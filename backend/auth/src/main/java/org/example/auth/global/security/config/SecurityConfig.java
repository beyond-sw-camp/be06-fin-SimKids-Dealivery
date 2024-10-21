package org.example.auth.global.security.config;


import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.example.auth.global.security.custom.service.OAuth2Service;
import org.example.auth.global.security.filter.LoginFilter;
import org.example.auth.global.security.handler.*;

import org.example.auth.global.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtUtil jwtUtil;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final AccessDeniedHandler accessDeniedHandler;
    private final LoginFailureHandler loginFailureHandler;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;
    private final OAuth2Service oAuth2Service;
    @Qualifier("masterRedisTemplate")
    private final RedisTemplate<String, Object> masterRedisTemplate;
    @Qualifier("slaveRedisTemplate")
    private final RedisTemplate<String, Object> slaveRedisTemplate;
    private final CustomLogoutHandler customLogoutHandler;

    public SecurityConfig(JwtUtil jwtUtil,
                          AuthenticationConfiguration authenticationConfiguration,
                          AccessDeniedHandler accessDeniedHandler,
                          LoginFailureHandler loginFailureHandler,
                          OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler,
                          CustomLogoutSuccessHandler customLogoutSuccessHandler,
                          OAuth2Service oAuth2Service,
                          @Qualifier("masterRedisTemplate") RedisTemplate<String, Object> masterRedisTemplate,
                          @Qualifier("slaveRedisTemplate") RedisTemplate<String, Object> slaveRedisTemplate,
                          CustomLogoutHandler customLogoutHandler) {
        this.jwtUtil = jwtUtil;
        this.authenticationConfiguration = authenticationConfiguration;
        this.accessDeniedHandler = accessDeniedHandler;
        this.loginFailureHandler = loginFailureHandler;
        this.oAuth2AuthenticationSuccessHandler = oAuth2AuthenticationSuccessHandler;
        this.customLogoutSuccessHandler = customLogoutSuccessHandler;
        this.oAuth2Service = oAuth2Service;
        this.masterRedisTemplate = masterRedisTemplate;
        this.slaveRedisTemplate = slaveRedisTemplate;
        this.customLogoutHandler = customLogoutHandler;
    }

    @Value("${domain}")
    private String domain;
  
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:8081"); // 허용할 출처
        config.addAllowedOrigin("http://localhost:3000"); // 허용할 출처
        config.addAllowedOrigin(domain); // 허용할 출처
        config.addAllowedOrigin(domain + "/api"); // 허용할 출처
        config.addAllowedOriginPattern("*"); // 허용할 출처
        config.addAllowedMethod("*"); // 허용할 메서드 (GET, POST, PUT 등)
        config.addAllowedHeader("*"); // 허용할 헤더
        config.setAllowCredentials(true); // 자격 증명 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((auth) -> auth.disable());
        http.httpBasic((auth) -> auth.disable());
        http.sessionManagement((auth) -> auth.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.formLogin((auth) -> auth.disable());
        http.authorizeHttpRequests((auth) ->
                auth
                        .requestMatchers("/user/**").permitAll() // 모든 사람 접속 가능
                        .requestMatchers("/v2/api-dosc", "/swagger-resources/**", "/swagger-ui.html","/webjars/**","/swagger-ui/**" ).permitAll()
                        .anyRequest().permitAll()

        );
        //로그아웃 처리
        http.logout(logout -> {
            logout.logoutUrl("/logout")
                    .logoutSuccessHandler(customLogoutSuccessHandler)
                    .addLogoutHandler(customLogoutHandler)
                    .addLogoutHandler(new CookieClearingLogoutHandler(
                            new Cookie("type", null) {{
                                setPath("/");// root 경로의 쿠키 삭제
                                setMaxAge(0);
                            }},
                            new Cookie("AToken", null) {{
                                setPath("/");  // root 경로의 쿠키 삭제
                                setMaxAge(0);
                            }},
                            new Cookie("RToken", null) {{
                                setPath("/");  // root 경로의 쿠키 삭제
                                setMaxAge(0);
                            }}
                    ))
                    .invalidateHttpSession(true)
                    .deleteCookies("AToken", "RToken", "type")
                    .permitAll();
        });

        http.oauth2Login(
            oauth2Login -> oauth2Login.authorizationEndpoint(
                authorizationEndpointConfig -> authorizationEndpointConfig
                    .baseUri("/oauth/oauth2/authorization")
                )
                .redirectionEndpoint(
                    redirectionEndpointConfig -> redirectionEndpointConfig
                        .baseUri(("/oauth/login/oauth2/code/*"))
                )
            );

        //필터생성 및 설정추가
        LoginFilter loginFilter = new LoginFilter(masterRedisTemplate,slaveRedisTemplate,jwtUtil, authenticationManager(authenticationConfiguration));
        loginFilter.setFilterProcessesUrl("/login");
        loginFilter.setAuthenticationFailureHandler(loginFailureHandler);

        //예외처리 핸들러 추가
        http.exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedHandler(accessDeniedHandler));

        //OAuth관련 설정 추가
        http.oauth2Login((config) -> {
            config.successHandler(oAuth2AuthenticationSuccessHandler);
            config.userInfoEndpoint((endPoint) -> endPoint.userService(oAuth2Service));
        });

        http.addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
