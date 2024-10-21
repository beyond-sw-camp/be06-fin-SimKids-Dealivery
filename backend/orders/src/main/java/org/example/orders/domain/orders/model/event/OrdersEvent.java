package org.example.orders.domain.orders.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.orders.domain.board.model.entity.ProductBoard;
import org.example.orders.domain.company.model.entity.Company;
import org.example.orders.domain.product.model.dto.ProductDto;
import org.example.orders.domain.product.model.entity.Product;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class OrdersEvent {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardRegisterEvent {
        private Long idx;
        private String title;
        private Long companyIdx;
        private Integer discountRate;
        private String productThumbnailUrl;
        private List<ProductDto.RegisteredProduct> products;
        private Integer minimumPrice;
        private LocalDateTime endedAt;

        public ProductBoard toEntity(Company company){
            return ProductBoard.builder()
                    .idx(this.idx)
                    .title(this.title)
                    .company(company)
                    .discountRate(this.discountRate)
                    .productThumbnailUrl(this.productThumbnailUrl)
                    .endedAt(this.endedAt)
                    .minimumPrice(this.minimumPrice)
                    .build();
        }

    }

}
