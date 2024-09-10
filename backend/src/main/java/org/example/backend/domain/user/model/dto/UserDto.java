package org.example.backend.domain.user.model.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.backend.domain.user.model.entity.User;

import java.time.LocalDateTime;

public class UserDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UserSignupRequest{
        @NotBlank
        @Size(max = 10, message = "이름은 10자까지 입력 가능합니다.")
        private String name;

        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
                message = "아이디는 이메일 형식이어야 합니다.")
        @Size(max = 40, message = "아이디는 최대 40자까지 입력 가능합니다.")
        private String email;

        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9]{6}$", message = "인증코드의 형식이 올바르지 않습니다.")
        private String emailCode;

        @NotBlank
        @Size(max = 20, message = "비밀번호는 최대 20자까지 입력 가능합니다.")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[~!@#])[A-Za-z\\d~!@#]{8,}$",
        message = "비밀번호는 대문자,소문자,숫자,특수문자 (~!@#) 각각 1개 이상 포함하여 8글자 이상이어야 합니다.")
        private String password;

        @NotBlank
        @Pattern(regexp = "^(010)-\\d{4}-\\d{4}$", message = "휴대폰 번호 형식이 올바르지 않습니다. (-를 포함해서 입력해주세요)")
        private String phoneNumber;

        @NotBlank
        @Size(max = 6, message = "우편번호는 최대 6자까지 입력 가능합니다.")
        @Pattern(regexp = "^\\d{5,}$", message = "우편번호 형식이 올바르지 않습니다.")
        private String postNumber;

        @NotBlank
        @Size(max = 30, message = "주소는 최대 30자까지 입력 가능합니다.")
        private String address;

        @NotBlank
        @Size(max = 30, message = "상세주소는 최대 30자까지 입력 가능합니다.")
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
                    .role("ROLE_USER")
                    .phoneNumber(this.phoneNumber)
                    .postNumber(this.postNumber)
                    .type(this.type)
                    .registeredAt(LocalDateTime.now())
                    .point(0L)
                    .build();
        }
    }
}
