package org.example.board.domain.likes.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLikes is a Querydsl query type for Likes
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLikes extends EntityPathBase<Likes> {

    private static final long serialVersionUID = -298736708L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLikes likes = new QLikes("likes");

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final org.example.board.domain.board.model.entity.QProductBoard productBoard;

    public final org.example.board.domain.user.model.entity.QUser user;

    public QLikes(String variable) {
        this(Likes.class, forVariable(variable), INITS);
    }

    public QLikes(Path<? extends Likes> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLikes(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLikes(PathMetadata metadata, PathInits inits) {
        this(Likes.class, metadata, inits);
    }

    public QLikes(Class<? extends Likes> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productBoard = inits.isInitialized("productBoard") ? new org.example.board.domain.board.model.entity.QProductBoard(forProperty("productBoard"), inits.get("productBoard")) : null;
        this.user = inits.isInitialized("user") ? new org.example.board.domain.user.model.entity.QUser(forProperty("user")) : null;
    }

}

