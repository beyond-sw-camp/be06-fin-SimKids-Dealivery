package org.example.orders.domain.company.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCompany is a Querydsl query type for Company
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCompany extends EntityPathBase<Company> {

    private static final long serialVersionUID = 1762660407L;

    public static final QCompany company = new QCompany("company");

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final ListPath<org.example.orders.domain.board.model.entity.ProductBoard, org.example.orders.domain.board.model.entity.QProductBoard> productBoards = this.<org.example.orders.domain.board.model.entity.ProductBoard, org.example.orders.domain.board.model.entity.QProductBoard>createList("productBoards", org.example.orders.domain.board.model.entity.ProductBoard.class, org.example.orders.domain.board.model.entity.QProductBoard.class, PathInits.DIRECT2);

    public QCompany(String variable) {
        super(Company.class, forVariable(variable));
    }

    public QCompany(Path<? extends Company> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCompany(PathMetadata metadata) {
        super(Company.class, metadata);
    }

}

