package org.example.board.domain.board.category.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.board.domain.board.model.entity.ProductBoard;

import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;

	@Column(unique = true)
	private String name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private List<ProductBoard> productBoards;

}
