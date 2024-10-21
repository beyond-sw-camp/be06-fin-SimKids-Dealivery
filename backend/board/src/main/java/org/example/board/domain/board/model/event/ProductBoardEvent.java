package org.example.board.domain.board.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.board.domain.board.product.model.dto.ProductDto;

import java.time.LocalDateTime;
import java.util.List;

public class ProductBoardEvent {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoardRegisterCompleteEvent {
        private Long idx;
        private String title;
        private Long companyIdx;
        private Integer discountRate;
        private String productThumbnailUrl;
        private List<ProductDto.RegisteredProduct> products;
        private LocalDateTime endedAt;
        private Integer minimumPrice;
    }
}
