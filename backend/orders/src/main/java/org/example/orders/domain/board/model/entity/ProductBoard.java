package org.example.orders.domain.board.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.orders.domain.company.model.entity.Company;
import org.example.orders.domain.product.model.entity.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductBoard {

    @Id
    private Long idx;

    private String title;

    private String productThumbnailUrl;
    private Integer minimumPrice;

    private Integer discountRate;


    private LocalDateTime endedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_idx")
    private Company company;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productBoard")
    private List<Product> products = new ArrayList<>();
}
