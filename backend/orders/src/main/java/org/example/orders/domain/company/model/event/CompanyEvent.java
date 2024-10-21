package org.example.orders.domain.company.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.orders.domain.company.model.entity.Company;

public class CompanyEvent {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CompanySignupCompleteEvent {
        private Long idx;

        public Company toEntity(){
            return Company.builder()
                    .idx(this.idx)
                    .build();
        }
    }
}
