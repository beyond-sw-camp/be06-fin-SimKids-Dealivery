package org.example.board.domain.board.repository;

import org.example.board.domain.board.model.entity.ProductThumbnailImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductThumbnailImageRepository extends JpaRepository<ProductThumbnailImage, Long> {
	// @Query("select pti from ProductThumbnailImage pti where pti.productBoard.idx = :idx")
	Optional<List<ProductThumbnailImage>> findAllByProductBoardIdx(Long idx);
}
