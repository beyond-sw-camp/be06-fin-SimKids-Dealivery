package org.example.backend.global.security.config;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.backend.global.security.custom.auth.CustomAuthenticationProvider;
import org.example.backend.global.security.custom.service.CustomUserDetailService;
import org.example.backend.global.security.filter.JwtFilter;
import org.example.backend.global.security.filter.LoginFilter;
import org.example.backend.global.security.handler.AccessDeniedHandler;
import org.example.backend.global.security.handler.LoginFailureHandler;
import org.example.backend.global.security.jwt.JwtUtil;
import org.example.backend.global.security.jwt.repository.CompanyRefreshTokenRepository;
import org.example.backend.global.security.jwt.repository.UserRefreshTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtUtil jwtUtil;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final UserRefreshTokenRepository userRefreshTokenRepository;
    private final CompanyRefreshTokenRepository companyRefreshTokenRepository;
    private final AccessDeniedHandler accessDeniedHandler;
    private final LoginFailureHandler loginFailureHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:8081"); // 허용할 출처
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



        //필터추가
        LoginFilter loginFilter = new LoginFilter(jwtUtil, authenticationManager(authenticationConfiguration)
                ,companyRefreshTokenRepository,userRefreshTokenRepository);
        loginFilter.setFilterProcessesUrl("/login");
        loginFilter.setAuthenticationFailureHandler(loginFailureHandler);
        http.exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedHandler(accessDeniedHandler));

        http.addFilterBefore(new JwtFilter(jwtUtil, companyRefreshTokenRepository, userRefreshTokenRepository), LoginFilter.class);
        http.addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
