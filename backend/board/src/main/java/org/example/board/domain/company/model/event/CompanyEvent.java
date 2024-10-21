package org.example.board.domain.company.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.board.domain.company.model.entity.Company;

public class CompanyEvent {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CompanySignupCompleteEvent {
        private Long idx;
        private String companyName;
        private String email;

        public Company toEntity() {
            return Company.builder()
                    .idx(this.idx)
                    .companyName(this.companyName)
                    .email(this.email)
                    .build();
        }
    }
}
