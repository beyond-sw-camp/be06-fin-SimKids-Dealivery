package org.example.backend.domain.board.repository.querydsl;

import org.example.backend.domain.board.model.entity.ProductBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductBoardRepositoryCustom {
	Page<ProductBoard> search(String search, Pageable pageable);
	Page<ProductBoard> companySearch(String status, Integer month, Pageable pageable);
}
