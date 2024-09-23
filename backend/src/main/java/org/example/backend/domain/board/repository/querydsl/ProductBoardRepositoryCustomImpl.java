package org.example.backend.domain.board.repository.querydsl;

import java.time.LocalDateTime;
import java.util.List;

import org.example.backend.domain.board.category.model.entity.QCategory;
import org.example.backend.domain.board.model.entity.ProductBoard;
import org.example.backend.domain.board.model.entity.QProductBoard;
import org.example.backend.domain.company.model.entity.QCompany;
import org.example.backend.global.common.constants.BoardStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Repository
public class ProductBoardRepositoryCustomImpl implements ProductBoardRepositoryCustom{
	private final JPAQueryFactory queryFactory;
	private final QProductBoard qProductBoard;
	private final QCategory qCategory;
	private final QCompany qCompany;

	public ProductBoardRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
		this.qProductBoard = QProductBoard.productBoard;
		this.qCategory = QCategory.category;
		this.qCompany = QCompany.company;
	}

	@Override
	public Page<ProductBoard> search(String search, Pageable pageable) {
		JPQLQuery<ProductBoard> query = queryFactory
			.selectFrom(qProductBoard)
			.leftJoin(qProductBoard.category, qCategory).fetchJoin()
			.leftJoin(qProductBoard.company, qCompany).fetchJoin()
			.where(getCondition(search));
		int count = query.fetch().size();

		List<ProductBoard> result = query
			.orderBy(qProductBoard.idx.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();
		return new PageImpl<>(result, pageable, count);
	}

	@Override
	public Page<ProductBoard> companySearch(Long companyIdx, String status, Integer month, Pageable pageable) {
		BooleanExpression condition = qProductBoard.company.idx.eq(companyIdx).and(getCondition(status, month));
		List<ProductBoard> result = queryFactory
			.selectFrom(qProductBoard)
			.leftJoin(qProductBoard.category, qCategory).fetchJoin()
			.where(condition)
			.orderBy(qProductBoard.idx.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		int total = queryFactory.selectFrom(qProductBoard)
			.where(condition)
			.fetch().size();

		return new PageImpl<>(result, pageable, total);
	}

	// ---- 전체 사용자 ----
	private BooleanBuilder getCondition(String search) {
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		BooleanExpression categoryCondition = containsCategory(search);
		BooleanExpression companyCondition = containsCompanyName(search);
		BooleanExpression titleCondition = containsTitle(search);
		BooleanExpression statusCondition = containsStatus(BoardStatus.OPEN.getStatus());

		if (categoryCondition == null && companyCondition == null && titleCondition == null && statusCondition == null) {
			return null;
		}
		if (categoryCondition != null) {
			booleanBuilder.or(categoryCondition);
		}
		if (companyCondition != null) {
			booleanBuilder.or(companyCondition);
		}
		if (titleCondition != null) {
			booleanBuilder.or(titleCondition);
		}
		if (search != null && search.equals(BoardStatus.READY.getStatus())) {
			booleanBuilder.or(containsStatus(BoardStatus.READY.getStatus()));
		} else if (statusCondition != null) {
			booleanBuilder.and(statusCondition);
		}
		return booleanBuilder;
	}

	private BooleanExpression containsCategory(String search) {
		return search == null ? null : qProductBoard.category.name.containsIgnoreCase(search);
	}

	private BooleanExpression containsCompanyName(String search) {
		return search == null ? null : qProductBoard.company.companyName.containsIgnoreCase(search);
	}

	private BooleanExpression containsTitle(String search) {
		return search == null ? null : qProductBoard.title.containsIgnoreCase(search);
	}

	private BooleanExpression containsStatus(String search) {
		return search == null ? null : qProductBoard.status.containsIgnoreCase(search);
	}


	// ---- 판매자 사용자 ----
	private BooleanExpression getCondition(String status, Integer month) {
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
