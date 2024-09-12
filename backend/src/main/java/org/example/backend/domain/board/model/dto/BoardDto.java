package org.example.backend.domain.board.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.example.backend.domain.board.product.model.dto.ProductDto;
import org.example.backend.global.common.constants.CategoryType;

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
		private List<ProductDto.Request> products;
		private LocalDateTime startedAt;
		private LocalDateTime endedAt;
		private CategoryType category;
	}
}
