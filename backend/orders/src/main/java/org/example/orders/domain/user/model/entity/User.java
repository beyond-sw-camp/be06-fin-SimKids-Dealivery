package org.example.orders.domain.user.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    private Long idx;

    private String name;

    private Long point;

    public void deductPoints(Long point) {
        this.point -= point;
    }

    public void earnPoints(Long point) {
        this.point += point;
    }
}
