package org.example.auth.domain.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.auth.domain.company.model.entity.Company;
import org.example.auth.domain.user.model.entity.User;

public class UserDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UserLoginRequest{
        private String email;
        private String password;
    }
}
