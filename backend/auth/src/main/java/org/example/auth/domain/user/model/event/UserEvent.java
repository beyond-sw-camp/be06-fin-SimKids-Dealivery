package org.example.auth.domain.user.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.auth.domain.user.model.entity.User;

public class UserEvent {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UserSignupCompleteEvent {
        private Long idx;
        private String email;
        private String password;
        private String role;
        private String type;

        public User toEntity() {
            return User.builder()
                    .idx(this.idx)
                    .email(this.email)
                    .password(this.password)
                    .role(this.role)
                    .type(this.type)
                    .build();
        }
    }
}
