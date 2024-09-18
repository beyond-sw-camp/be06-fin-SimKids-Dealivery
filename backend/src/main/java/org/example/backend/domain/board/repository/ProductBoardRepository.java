package org.example.backend.domain.board.repository;

import org.example.backend.domain.board.model.entity.ProductBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductBoardRepository extends JpaRepository<ProductBoard, Long> {
	@Query("SELECT pb FROM ProductBoard pb JOIN FETCH pb.category")
	Page<ProductBoard> findAllWithCategory(Pageable pageable);
}
