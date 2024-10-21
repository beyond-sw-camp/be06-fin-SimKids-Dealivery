package org.example.board.domain.board.repository;

import org.example.board.domain.board.model.entity.ProductBoard;
import org.example.board.domain.board.repository.querydsl.ProductBoardRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductBoardRepository extends JpaRepository<ProductBoard, Long>, ProductBoardRepositoryCustom {
	@Query("SELECT pb FROM ProductBoard pb JOIN FETCH pb.category JOIN FETCH pb.company JOIN FETCH pb.productThumbnailImages WHERE pb.idx = :idx")
	Optional<ProductBoard> findByIdx(Long idx);

	@Query("SELECT pb FROM ProductBoard pb JOIN FETCH pb.category JOIN FETCH pb.productThumbnailImages WHERE pb.idx = :idx and pb.company.idx = :companyIdx")
	Optional<ProductBoard> findByCompanyIdxAndIdx(Long companyIdx, Long idx);

	List<ProductBoard> findByCompanyEmail(String companyEmail);
}
