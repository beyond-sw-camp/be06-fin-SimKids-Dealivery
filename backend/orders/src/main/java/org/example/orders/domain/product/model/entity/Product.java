package org.example.orders.domain.product.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.orders.domain.board.model.entity.ProductBoard;
import org.example.orders.domain.company.model.entity.Company;
import org.example.orders.domain.product.model.dto.ProductDto;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    private Long idx;

    private String name;

    private Integer price;

    private Integer stock;

    public void decreaseStock(Integer quantity) {
        this.stock -= quantity;
    }

    public void increaseStock(Integer quantity) {
        this.stock += quantity;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="product_board_idx")
    private ProductBoard productBoard;
}
