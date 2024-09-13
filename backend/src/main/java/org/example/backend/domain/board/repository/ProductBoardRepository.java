package org.example.backend.domain.board.repository;

import org.example.backend.domain.board.model.entity.ProductBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductBoardRepository extends JpaRepository<ProductBoard, Long> {
}
