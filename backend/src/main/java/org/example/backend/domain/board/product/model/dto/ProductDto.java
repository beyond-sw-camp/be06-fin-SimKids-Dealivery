package org.example.backend.domain.board.product.model.dto;

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
	}
}
