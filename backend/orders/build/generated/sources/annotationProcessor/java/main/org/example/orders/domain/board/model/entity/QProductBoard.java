package org.example.orders.domain.board.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductBoard is a Querydsl query type for ProductBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductBoard extends EntityPathBase<ProductBoard> {

    private static final long serialVersionUID = 1045948710L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductBoard productBoard = new QProductBoard("productBoard");

    public final org.example.orders.domain.company.model.entity.QCompany company;

    public final NumberPath<Integer> discountRate = createNumber("discountRate", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> endedAt = createDateTime("endedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final NumberPath<Integer> minimumPrice = createNumber("minimumPrice", Integer.class);

    public final ListPath<org.example.orders.domain.product.model.entity.Product, org.example.orders.domain.product.model.entity.QProduct> products = this.<org.example.orders.domain.product.model.entity.Product, org.example.orders.domain.product.model.entity.QProduct>createList("products", org.example.orders.domain.product.model.entity.Product.class, org.example.orders.domain.product.model.entity.QProduct.class, PathInits.DIRECT2);

    public final StringPath productThumbnailUrl = createString("productThumbnailUrl");

    public final StringPath title = createString("title");

    public QProductBoard(String variable) {
        this(ProductBoard.class, forVariable(variable), INITS);
    }

    public QProductBoard(Path<? extends ProductBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductBoard(PathMetadata metadata, PathInits inits) {
        this(ProductBoard.class, metadata, inits);
    }

    public QProductBoard(Class<? extends ProductBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.company = inits.isInitialized("company") ? new org.example.orders.domain.company.model.entity.QCompany(forProperty("company")) : null;
    }

}

