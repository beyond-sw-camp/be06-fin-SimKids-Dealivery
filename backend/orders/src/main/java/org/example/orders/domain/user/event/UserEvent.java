package org.example.orders.domain.user.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.orders.domain.user.model.entity.User;

public class UserEvent {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UserSignupCompleteEvent {
        private Long idx;
        private String name;
        private Long point;

        public User toEntity() {
            return User.builder()
                    .idx(this.idx)
                    .name(this.name)
                    .point(this.point)
                    .build();
        }
    }
}
