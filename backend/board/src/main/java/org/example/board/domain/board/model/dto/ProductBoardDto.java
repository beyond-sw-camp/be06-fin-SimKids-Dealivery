package org.example.board.domain.board.model.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.board.domain.board.category.model.entity.Category;
import org.example.board.domain.board.model.entity.ProductBoard;
import org.example.board.domain.board.model.entity.ProductThumbnailImage;
import org.example.board.domain.board.product.model.dto.ProductDto;
import org.example.board.domain.company.model.entity.Company;
import org.example.board.global.common.constants.BoardStatus;
import org.example.board.global.common.constants.CategoryType;
import org.example.board.global.validator.annotation.MinuteCheck;
import org.example.board.global.validator.annotation.TimeRangeCheck;

import java.time.LocalDateTime;
import java.util.List;

public class ProductBoardDto {

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class BoardCreateRequest{

		@NotNull
		@Size(min = 1, max = 50, message = "제목은 1자 이상, 50자 이하로 입력해야 합니다.")
		private String title;

		@NotNull
		@Min(value = 0, message = "할인율은 0 이상이어야 합니다.")
		@Max(value = 100, message = "할인율은 100 이하이어야 합니다.")
		private Integer discountRate;

		@NotNull
		@NotEmpty(message = "상품을 등록해야 합니다.")
		private List<ProductDto.Request> products;

		@NotNull
		@Future(message = "시작 시간은 현재 시간 이후로 설정해야 합니다.")
		@MinuteCheck
		@TimeRangeCheck
		private LocalDateTime startedAt;

		@NotNull
		@Future(message = "종료 시간은 현재 시간 이후로 설정해야 합니다.")
		@MinuteCheck
		private LocalDateTime endedAt;

		@NotNull
		private CategoryType category;

		public ProductBoard toEntity(Long companyIdx, String thumbnailUrl, String detailUrl, Category category) {
			Integer minimumPrice = this.products.stream()
				.map(ProductDto.Request::getPrice)
				.min(Integer::compareTo)
				.orElse(Integer.MAX_VALUE);

			return ProductBoard.builder()
				.company(Company.builder().idx(companyIdx).build())
				.title(this.title)
				.discountRate(this.discountRate)
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

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class BoardListResponse {
		private Long idx;
		private String productThumbnailUrl;
		private String title;
		private String companyName;
		private String status;
		private LocalDateTime startedAt;
		private LocalDateTime endedAt;
		private Boolean likes;
		private Integer price;
		private Integer discountRate;
	}

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class BoardDetailResponse {
		private List<String> productThumbnailUrls;
		private String productDetailUrl;
		private String title;
		private List<ProductDto.Response> products;
		private LocalDateTime startedAt;
		private LocalDateTime endedAt;
		private String companyName;
		private String category;
		private Boolean likes;
		private Integer price;
		private Integer discountRate;
	}

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class CompanyBoardListResponse {
		private Long idx;
		private String productThumbnailUrl;
		private String title;
		private String category;
		private String status;
		private LocalDateTime startedAt;
		private LocalDateTime endedAt;
	}

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class CompanyBoardDetailResponse {
		private List<String> productThumbnailUrls;
		private String productDetailUrl;
		private String title;
		private Integer discountRate;
		private List<ProductDto.CompanyResponse> products;
		private LocalDateTime startedAt;
		private LocalDateTime endedAt;
		private String category;
	}
}
