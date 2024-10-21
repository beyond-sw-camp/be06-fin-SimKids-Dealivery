package org.example.board.domain.board.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.board.domain.board.model.entity.ProductBoard;
import org.example.board.domain.board.product.model.entity.Product;

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

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Response {
		private Long idx;
		private String name;
		private Integer price;

		public Product toEntity(ProductBoard productBoard) {
			return Product.builder()
				.idx(this.idx)
				.name(this.name)
				.price(this.price)
				.productBoard(productBoard)
				.build();
		}
	}

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class CompanyResponse{
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

	@Getter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class RegisteredProduct{
		private Long idx;

		private String name;

		private Integer price;

		private Integer stock;
	}

	@Getter
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class OrderedProduct{
		private Long idx;

		private Integer quantity;
	}

}
