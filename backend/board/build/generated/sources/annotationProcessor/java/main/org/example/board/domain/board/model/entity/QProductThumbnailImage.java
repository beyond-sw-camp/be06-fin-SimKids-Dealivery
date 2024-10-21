package org.example.board.domain.board.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductThumbnailImage is a Querydsl query type for ProductThumbnailImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductThumbnailImage extends EntityPathBase<ProductThumbnailImage> {

    private static final long serialVersionUID = -798295244L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductThumbnailImage productThumbnailImage = new QProductThumbnailImage("productThumbnailImage");

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final QProductBoard productBoard;

    public final StringPath productThumbnailUrl = createString("productThumbnailUrl");

    public QProductThumbnailImage(String variable) {
        this(ProductThumbnailImage.class, forVariable(variable), INITS);
    }

    public QProductThumbnailImage(Path<? extends ProductThumbnailImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductThumbnailImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductThumbnailImage(PathMetadata metadata, PathInits inits) {
        this(ProductThumbnailImage.class, metadata, inits);
    }

    public QProductThumbnailImage(Class<? extends ProductThumbnailImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productBoard = inits.isInitialized("productBoard") ? new QProductBoard(forProperty("productBoard"), inits.get("productBoard")) : null;
    }

}

