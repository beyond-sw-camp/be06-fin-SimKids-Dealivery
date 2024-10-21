package org.example.board.domain.board.product.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.board.domain.board.model.entity.ProductBoard;
import org.example.board.domain.board.product.model.dto.ProductDto;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	private String name;
	private Integer stock;
	private Integer price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="product_board_idx")
	private ProductBoard productBoard;

  public void decreaseStock(Integer quantity) {
  this.stock -= quantity;
  }

	public void increaseStock(Integer quantity) {
		this.stock += quantity;
	}

	public ProductDto.Response toResponse() {
		return ProductDto.Response.builder()
			.idx(this.idx)
			.name(this.name)
			.price(this.price)
			.build();
	}

	public ProductDto.CompanyResponse toCompanyResponse() {
		return ProductDto.CompanyResponse.builder()
			.name(this.name)
			.stock(this.stock)
			.price(this.price)
			.build();
	}

	public ProductDto.RegisteredProduct toRegisteredProduct(){
	  return ProductDto.RegisteredProduct.builder()
			  .idx(this.idx)
			  .name(this.name)
			  .price(this.price)
			  .stock(this.stock)
			  .build();
	}
}
