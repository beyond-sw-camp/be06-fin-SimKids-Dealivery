package org.example.backend.domain.board.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.example.backend.domain.board.category.model.entity.Category;
import org.example.backend.domain.board.product.model.entity.Product;
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
}
