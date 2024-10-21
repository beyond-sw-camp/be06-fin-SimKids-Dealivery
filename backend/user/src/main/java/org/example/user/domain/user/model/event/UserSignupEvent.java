package org.example.user.domain.user.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.user.domain.user.model.entity.User;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupEvent {
    private Long idx;
    private String name;
    private String email;
    private String password;
    private Long point;
    private String role;
    private String type;

    public User toEntity(){
        return User.builder()
                .idx(this.idx)
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .point(this.point)
                .role(this.role)
                .type(this.type)
                .build();
    }
}
