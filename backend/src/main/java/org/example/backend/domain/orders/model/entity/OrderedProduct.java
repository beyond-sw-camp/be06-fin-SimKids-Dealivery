package org.example.backend.domain.orders.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.example.backend.domain.board.product.model.entity.Product;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name="orders_idx")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name="product_idx")
    private Product product;
}
