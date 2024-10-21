package org.example.board.domain.board.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductThumbnailImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;

	@Column(length = 1000)
	private String productThumbnailUrl;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_board_idx")
	private ProductBoard productBoard;
}
