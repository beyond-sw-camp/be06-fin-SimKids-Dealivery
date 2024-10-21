package org.example.orders.domain.company.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.orders.domain.board.model.entity.ProductBoard;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    @Id
    private Long idx;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private List<ProductBoard> productBoards = new ArrayList<>();

}
