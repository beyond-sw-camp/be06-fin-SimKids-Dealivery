package org.example.board.domain.board.category.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCategory is a Querydsl query type for Category
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCategory extends EntityPathBase<Category> {

    private static final long serialVersionUID = -873977104L;

    public static final QCategory category = new QCategory("category");

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<org.example.board.domain.board.model.entity.ProductBoard, org.example.board.domain.board.model.entity.QProductBoard> productBoards = this.<org.example.board.domain.board.model.entity.ProductBoard, org.example.board.domain.board.model.entity.QProductBoard>createList("productBoards", org.example.board.domain.board.model.entity.ProductBoard.class, org.example.board.domain.board.model.entity.QProductBoard.class, PathInits.DIRECT2);

    public QCategory(String variable) {
        super(Category.class, forVariable(variable));
    }

    public QCategory(Path<? extends Category> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategory(PathMetadata metadata) {
        super(Category.class, metadata);
    }

}

