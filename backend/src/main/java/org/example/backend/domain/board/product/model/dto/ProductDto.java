package org.example.backend.domain.board.product.model.dto;

import org.example.backend.domain.board.model.entity.ProductBoard;
import org.example.backend.domain.board.product.model.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProductDto {

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Request{
		private String name;
		private Integer price;
		private Integer stock;

		public Product toEntity(ProductBoard productBoard) {
			return Product.builder()
				.name(this.name)
				.price(this.price)
				.stock(this.stock)
				.productBoard(productBoard)
				.build();
		}
	}
}
