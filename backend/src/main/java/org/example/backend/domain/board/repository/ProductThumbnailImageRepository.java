package org.example.backend.domain.board.repository;

import org.example.backend.domain.board.model.entity.ProductThumbnailImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductThumbnailImageRepository extends JpaRepository<ProductThumbnailImage, Long> {
}
