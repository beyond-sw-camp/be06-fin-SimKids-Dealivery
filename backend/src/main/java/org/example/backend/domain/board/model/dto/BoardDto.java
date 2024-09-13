package org.example.backend.domain.board.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.example.backend.domain.board.category.model.entity.Category;
import org.example.backend.domain.board.model.entity.ProductBoard;
import org.example.backend.domain.board.model.entity.ProductThumbnailImage;
import org.example.backend.domain.board.product.model.dto.ProductDto;
import org.example.backend.domain.board.product.model.entity.Product;
import org.example.backend.global.common.constants.BoardStatus;
import org.example.backend.global.common.constants.CategoryType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BoardDto {

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class BoardCreateRequest{
		private String title;
		private Integer discountRate;
		private List<ProductDto.Request> products;
		private LocalDateTime startedAt;
		private LocalDateTime endedAt;
		private CategoryType category;

		public ProductBoard toEntity(String thumbnailUrl, String detailUrl, Category category) {
			List<Product> products = this.products.stream()
				.map(ProductDto.Request::toEntity)
				.collect(Collectors.toList());

			Integer minimumPrice = this.products.stream()
				.map(ProductDto.Request::getPrice)
				.min(Integer::compareTo)
				.orElse(Integer.MAX_VALUE);

			return ProductBoard.builder()
				.title(this.title)
				.discountRate(this.discountRate)
				.products(products)
				.startedAt(this.startedAt)
				.endedAt(this.endedAt)
				.category(category)
				.productThumbnailUrl(thumbnailUrl)
				.productDetailUrl(detailUrl)
				.status(BoardStatus.READY.getStatus())
				.minimumPrice(minimumPrice)
				.build();
		}
		public ProductThumbnailImage toEntity(String thumbnailUrl, ProductBoard productBoard) {
			return ProductThumbnailImage.builder()
				.productBoard(productBoard)
				.productThumbnailUrl(thumbnailUrl)
				.build();
		}
	}
}
