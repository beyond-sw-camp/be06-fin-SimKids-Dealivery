package org.example.backend.domain.board.repository;

import java.util.List;
import java.util.Optional;

import org.example.backend.domain.board.model.entity.ProductThumbnailImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductThumbnailImageRepository extends JpaRepository<ProductThumbnailImage, Long> {
	Optional<List<ProductThumbnailImage>> findAllByProductBoardIdx(Long productBoardIdx);
}
