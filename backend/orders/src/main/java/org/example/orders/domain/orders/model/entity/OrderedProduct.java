package org.example.orders.domain.orders.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.orders.domain.board.model.entity.ProductBoard;
import org.example.orders.domain.product.model.dto.ProductDto;
import org.example.orders.domain.product.model.entity.Product;
import org.example.orders.domain.orders.model.dto.OrderedProductDto;

@Entity
@Builder
@Getter
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

    private Long productIdx;

    public OrderedProductDto.OrderedProductResponse toOrderedProductResponse(Product product, Integer discountRate) {
        return OrderedProductDto.OrderedProductResponse.builder()
                .name(product.getName())
                .amount(quantity)
                .price(Math.round(product.getPrice() * quantity * (1 - discountRate / 100.0)))
                .build();
    }

    public ProductDto.OrderedProductInfo toOrderedProductInfo(){
        return ProductDto.OrderedProductInfo.builder()
                .idx(this.productIdx)
                .quantity(this.quantity)
                .build();
    }
}
