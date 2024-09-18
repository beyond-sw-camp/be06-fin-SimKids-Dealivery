package org.example.backend.domain.orders.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.backend.domain.orders.model.dto.OrderDto.OrderCompleteRequest;
import org.example.backend.global.common.constants.OrderStatus;
import org.example.backend.global.common.constants.PaymentType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private Long boardIdx;

    private String ordersNumber; // 주문 번호

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    private String receiverName;
    private String receiverPhoneNumber;

    private String address;
    private String addressDetail;
    private String postNumber;

    private String paymentId;
    @Enumerated(EnumType.STRING)
    private PaymentType payMethod;
    private Integer usedPoint;

    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
    private List<OrderedProduct> orderedProducts;

    public void update(OrderCompleteRequest dto) {
        this.address = dto.getAddress();
        this.addressDetail = dto.getAddressDetail();
        this.postNumber = dto.getPostNumber();
        this.paymentId = dto.getPaymentId();
        this.payMethod = dto.getPayMethod();
        this.usedPoint = dto.getUsedPoint();
        this.modifiedAt = LocalDateTime.now();
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
