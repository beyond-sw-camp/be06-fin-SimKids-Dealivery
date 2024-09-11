package org.example.backend.domain.user.model.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.backend.domain.user.model.entity.User;
import org.example.backend.global.common.constants.Role;

import java.time.LocalDateTime;

public class UserDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UserSignupRequest{
        @NotBlank
        @Size(max = 10)
        private String name;

        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
        @Size(max = 40)
        private String email;

        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9]{6}$")
        private String emailCode;

        @NotBlank
        @Size(max = 20)
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[~!@#])[A-Za-z\\d~!@#]{8,}$")
        private String password;

        @NotBlank
        @Pattern(regexp = "^(010)-\\d{4}-\\d{4}$")
        private String phoneNumber;

        @NotBlank
        @Size(max = 6)
        @Pattern(regexp = "^\\d{5,}$")
        private String postNumber;

        @NotBlank
        @Size(max = 30)
        private String address;

        @NotBlank
        @Size(max = 30)
        private String addressDetail;

        @NotBlank
        private String type;

        public User toEntity(String encodedPassword){
            return User.builder()
                    .name(this.name)
                    .email(this.email)
                    .password(encodedPassword)
                    .address(this.address)
                    .addressDetail(this.addressDetail)
                    .emailStatus(true)
                    .status(true)
                    .role(Role.ROLE_USER.getRole())
                    .phoneNumber(this.phoneNumber)
                    .postNumber(this.postNumber)
                    .type(this.type)
                    .registeredAt(LocalDateTime.now())
                    .point(0L)
                    .build();
        }
    }
}
