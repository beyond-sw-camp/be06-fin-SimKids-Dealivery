package org.example.orders.domain.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.orders.domain.board.model.entity.ProductBoard;
import org.example.orders.domain.product.model.entity.Product;

public class ProductDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisteredProduct{
        private Long idx;

        private String name;

        private Integer price;

        private Integer stock;

        public Product toEntity(ProductBoard productBoard){
            return Product.builder()
                    .idx(this.idx)
                    .name(this.name)
                    .price(this.price)
                    .stock(this.stock)
                    .productBoard(productBoard)
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderedProductInfo{
        private Long idx;

        private Integer quantity;
    }
}
