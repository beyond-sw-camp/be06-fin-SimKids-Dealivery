package org.example.gateway.global.security.model.dto;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class CustomUserDetails implements UserDetails {
    private Long idx;
    private String email;
    private String role;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Long idx, String email, String role, Collection<? extends GrantedAuthority> authorities) {
        this.idx = idx;
        this.email = email;
        this.role = role;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null; // 비밀번호는 JWT에서 사용하지 않음
    }

    @Override
    public String getUsername() {
        return email; // 이메일을 사용자 이름으로 사용
    }
}
