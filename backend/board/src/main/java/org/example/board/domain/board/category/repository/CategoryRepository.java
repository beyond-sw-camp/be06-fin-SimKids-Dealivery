package org.example.board.domain.board.category.repository;

import org.example.board.domain.board.category.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	Category findByName(String name);
}
