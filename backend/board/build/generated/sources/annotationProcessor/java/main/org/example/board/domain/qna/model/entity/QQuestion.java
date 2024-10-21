package org.example.board.domain.qna.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestion is a Querydsl query type for Question
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestion extends EntityPathBase<Question> {

    private static final long serialVersionUID = 1653127950L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestion question = new QQuestion("question");

    public final ListPath<Answer, QAnswer> answers = this.<Answer, QAnswer>createList("answers", Answer.class, QAnswer.class, PathInits.DIRECT2);

    public final StringPath answerStatus = createString("answerStatus");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final DateTimePath<java.time.LocalDateTime> modifiedAt = createDateTime("modifiedAt", java.time.LocalDateTime.class);

    public final org.example.board.domain.board.model.entity.QProductBoard productBoard;

    public final StringPath title = createString("title");

    public final org.example.board.domain.user.model.entity.QUser user;

    public QQuestion(String variable) {
        this(Question.class, forVariable(variable), INITS);
    }

    public QQuestion(Path<? extends Question> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestion(PathMetadata metadata, PathInits inits) {
        this(Question.class, metadata, inits);
    }

    public QQuestion(Class<? extends Question> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.productBoard = inits.isInitialized("productBoard") ? new org.example.board.domain.board.model.entity.QProductBoard(forProperty("productBoard"), inits.get("productBoard")) : null;
        this.user = inits.isInitialized("user") ? new org.example.board.domain.user.model.entity.QUser(forProperty("user")) : null;
    }

}

