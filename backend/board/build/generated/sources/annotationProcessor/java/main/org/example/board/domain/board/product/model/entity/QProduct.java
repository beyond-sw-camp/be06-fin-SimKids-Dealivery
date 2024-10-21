package org.example.board.domain.board.product.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = -2013106268L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct product = new QProduct("product");

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final org.example.board.domain.board.model.entity.QProductBoard productBoard;

    public final NumberPath<Integer> stock = createNumber("stock", Integer.class);

    public QProduct(String variable) {
        this(Product.class, forVariable(variable), INITS);
    }

    public QProduct(Path<? extends Product> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct(PathMetadata metadata, PathInits inits) {
        this(Product.class, metadata, inits);
    }

    public QProduct(Class<? extends Product> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productBoard = inits.isInitialized("productBoard") ? new org.example.board.domain.board.model.entity.QProductBoard(forProperty("productBoard"), inits.get("productBoard")) : null;
    }

}

