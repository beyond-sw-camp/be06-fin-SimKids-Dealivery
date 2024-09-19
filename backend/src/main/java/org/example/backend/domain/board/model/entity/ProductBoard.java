package org.example.backend.domain.board.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.example.backend.domain.board.category.model.entity.Category;
import org.example.backend.domain.board.model.dto.BoardDto;
import org.example.backend.domain.board.product.model.dto.ProductDto;
import org.example.backend.domain.board.product.model.entity.Product;
import org.example.backend.global.common.constants.BoardStatus;
import org.example.backend.domain.qna.model.entity.Question;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ProductBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	private String title;
	@Column(length = 1000)
	private String productDetailUrl;
	private LocalDateTime startedAt;
	private LocalDateTime endedAt;
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdAt;
	@LastModifiedDate
	private LocalDateTime modifiedAt;
	@Column(length = 1000)
	private String productThumbnailUrl;
	private String status;
	private Integer minimumPrice;
	private Integer discountRate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productBoard")
	private List<ProductThumbnailImage> productThumbnailImages = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productBoard")
	private List<Product> products = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_idx")
	private Category category;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productBoard")
	private List<Question> questions = new ArrayList<>();

	// 판매자 게시글 목록 조회 DTO
	public BoardDto.BoardListResponse toBoardListResponse() {
		/* TODO
		status 상태를 startedAt과 endedAt을 통해 자동으로 DB에 업데이트 해야함
		추후 현재는 게시글 조회해올때마다 새로 값 세팅 해줌 -> Quartz 와 같은 스케줄링 라이브러리로 개선 예정
		* */
		return BoardDto.BoardListResponse.builder()
			.idx(this.idx)
			.productThumbnailUrl(this.productThumbnailUrl)
			.title(this.title)
			.category(this.category.getName())
			.status(BoardStatus.calculateStatus(this.startedAt, this.endedAt).getStatus()) // <- 이부분 수정해야됨
			.startedAt(this.startedAt)
			.endedAt(this.endedAt)
			.build();
	}

	public BoardDto.BoardDetailResponse toBoardDetailResponse(List<String> productDetailUrls, List<ProductDto.Request> products) {
		return BoardDto.BoardDetailResponse.builder()
			.productThumbnailUrls(productDetailUrls)
			.productDetailUrl(this.productDetailUrl)
			.title(this.title)
			.discountRate(this.discountRate)
			.products(products)
			.startedAt(this.startedAt.withSecond(0).withNano(0))
			.endedAt(this.endedAt.withSecond(0).withNano(0))
			.category(this.category.getName())
			.build();
	}
}
