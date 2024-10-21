package org.example.orders.domain.orders.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrders is a Querydsl query type for Orders
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrders extends EntityPathBase<Orders> {

    private static final long serialVersionUID = 550071633L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrders orders = new QOrders("orders");

    public final StringPath address = createString("address");

    public final StringPath addressDetail = createString("addressDetail");

    public final NumberPath<Long> boardIdx = createNumber("boardIdx", Long.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> idx = createNumber("idx", Long.class);

    public final DateTimePath<java.time.LocalDateTime> modifiedAt = createDateTime("modifiedAt", java.time.LocalDateTime.class);

    public final ListPath<OrderedProduct, QOrderedProduct> orderedProducts = this.<OrderedProduct, QOrderedProduct>createList("orderedProducts", OrderedProduct.class, QOrderedProduct.class, PathInits.DIRECT2);

    public final StringPath ordersNumber = createString("ordersNumber");

    public final NumberPath<Long> originalPaidAmount = createNumber("originalPaidAmount", Long.class);

    public final StringPath paymentId = createString("paymentId");

    public final EnumPath<org.example.orders.global.constants.PaymentType> payMethod = createEnum("payMethod", org.example.orders.global.constants.PaymentType.class);

    public final StringPath postNumber = createString("postNumber");

    public final StringPath receiverName = createString("receiverName");

    public final StringPath receiverPhoneNumber = createString("receiverPhoneNumber");

    public final EnumPath<org.example.orders.global.constants.OrderStatus> status = createEnum("status", org.example.orders.global.constants.OrderStatus.class);

    public final NumberPath<Long> totalPaidAmount = createNumber("totalPaidAmount", Long.class);

    public final NumberPath<Long> usedPoint = createNumber("usedPoint", Long.class);

    public final org.example.orders.domain.user.model.entity.QUser user;

    public QOrders(String variable) {
        this(Orders.class, forVariable(variable), INITS);
    }

    public QOrders(Path<? extends Orders> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrders(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrders(PathMetadata metadata, PathInits inits) {
        this(Orders.class, metadata, inits);
    }

    public QOrders(Class<? extends Orders> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new org.example.orders.domain.user.model.entity.QUser(forProperty("user")) : null;
    }

}

