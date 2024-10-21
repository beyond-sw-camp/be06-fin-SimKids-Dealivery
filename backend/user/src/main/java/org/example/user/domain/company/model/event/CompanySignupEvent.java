package org.example.user.domain.company.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.user.domain.company.model.entity.Company;
import org.example.user.domain.user.model.entity.User;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanySignupEvent {
    private Long idx;
    private String name;
    private String email;
    private String password;
    private String role;

    public Company toEntity(){
        return Company.builder()
                .idx(this.idx)
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .role(this.role)
                .build();
    }
}
