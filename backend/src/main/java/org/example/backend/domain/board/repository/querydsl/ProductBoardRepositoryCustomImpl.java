package org.example.backend.domain.board.repository.querydsl;

import java.time.LocalDateTime;
import java.util.List;

import org.example.backend.domain.board.category.model.entity.QCategory;
import org.example.backend.domain.board.model.entity.ProductBoard;
import org.example.backend.domain.board.model.entity.QProductBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Repository
public class ProductBoardRepositoryCustomImpl implements ProductBoardRepositoryCustom{
	private final JPAQueryFactory queryFactory;
	private final QProductBoard qProductBoard;
	private final QCategory qCategory;
	public ProductBoardRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
		this.qProductBoard = QProductBoard.productBoard;
		this.qCategory = QCategory.category;
	}


	@Override
	public Page<ProductBoard> search(String status, Integer month, Pageable pageable) {
		BooleanExpression whereQuery = getQuery(status, month);
		List<ProductBoard> result = queryFactory
			.selectFrom(qProductBoard)
			.leftJoin(qProductBoard.category, qCategory).fetchJoin()
			.where(whereQuery)
			.orderBy(qProductBoard.idx.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		Long total = queryFactory.selectFrom(qProductBoard)
			.where(whereQuery)
			.fetchCount();

		return new PageImpl<>(result, pageable, total);
	}

	private BooleanExpression getQuery(String status, Integer month) {
		BooleanExpression statusExpression = statusEquals(status);
		BooleanExpression monthExpression = monthEquals(month);

		// 둘 다 null일 경우 전체 조회
		if (statusExpression == null && monthExpression == null) {
			return null;
			// 둘 다 null이 아니면 두 가지 조건으로 조회
		} else if (statusExpression != null && monthExpression != null) {
			return statusExpression.and(monthExpression);
		}
		// 둘 중 하나만 null이면 null이 아닌쪽을 기준으로 조회
		return statusExpression == null ? monthExpression : statusExpression;
	}

	private BooleanExpression statusEquals(String status) {
		return status == null ? null : qProductBoard.status.eq(status);
	}

	private BooleanExpression monthEquals(Integer month) {
		return month == null ? null : qProductBoard.startedAt.after(LocalDateTime.now().minusMonths(month));
	}
}
