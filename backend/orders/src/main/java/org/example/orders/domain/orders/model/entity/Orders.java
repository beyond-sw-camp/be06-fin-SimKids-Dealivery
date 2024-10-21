package org.example.orders.domain.orders.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.example.orders.domain.board.model.entity.ProductBoard;
import org.example.orders.domain.user.model.entity.User;
import org.example.orders.global.constants.OrderStatus;
import org.example.orders.global.constants.PaymentType;
import org.example.orders.domain.orders.model.dto.OrderDto;
import org.example.orders.domain.orders.model.dto.OrderedProductDto;
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
  
    private Long usedPoint;


    private Long totalPaidAmount;

    private Long originalPaidAmount;

    @ManyToOne
    @JoinColumn(name = "user_idx")
    private User user;

    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<OrderedProduct> orderedProducts;


    public void update(OrderDto.OrderCompleteRequest dto) {
        this.paymentId = dto.getPaymentId();
        this.totalPaidAmount = dto.getTotalPaidAmount();
        this.originalPaidAmount = dto.getOriginalPaidAmount();
        this.payMethod = dto.getPayMethod();
        this.usedPoint = dto.getUsedPoint();
        this.receiverName = dto.getReceiverName();
        this.receiverPhoneNumber = dto.getReceiverPhoneNumber();
        this.modifiedAt = LocalDateTime.now();
        this.address = dto.getAddress();
        this.addressDetail = dto.getAddressDetail();
        this.postNumber = dto.getPostNumber();
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }


    public OrderDto.CompanyOrderListResponse toCompanyOrderListResponse(String title) {
        return OrderDto.CompanyOrderListResponse.builder()
                .orderIdx(this.idx)
                .ordersNumber(this.ordersNumber)
                .title(title)
                .ordererName(this.user.getName())
                .status(this.status.getStatus())
                .totalPaidAmount(this.totalPaidAmount)
                .payMethod(this.payMethod.getType())
                .modifiedAt(this.modifiedAt)
                .build();
    }

    public OrderDto.CompanyOrderDetailResponse toCompanyOrderDetailResponse(List<OrderedProductDto.OrderedProductResponse> products) {
        return OrderDto.CompanyOrderDetailResponse.builder()
                .orderIdx(this.idx)
                .products(products)
                .build();
    }

    public OrderDto.UserOrderListResponse toUserOrderListResponse(ProductBoard board) {
        return OrderDto.UserOrderListResponse.builder()
                .orderIdx(this.idx)
                .ordersNumber(this.ordersNumber)
                .title(board.getTitle())
                .thumnailUrl(board.getProductThumbnailUrl())
                .minimumPrice(board.getMinimumPrice())
                .discountRate(board.getDiscountRate())
                .status(this.status.getStatus())
                .createdAt(this.createdAt)
                .build();
    }

    public OrderDto.UserOrderDetailResponse toUserOrderDetailResponse(ProductBoard board) {
        return OrderDto.UserOrderDetailResponse.builder()
                .ordersNumber(this.ordersNumber)
                .status(this.status.getStatus())
                .createdAt(this.createdAt)
                .payMethod(this.payMethod.getType())
                .usedPoint(this.usedPoint)
                .totalPaidAmount(this.totalPaidAmount)
                .originalPaidAmount(this.originalPaidAmount)
                .receiverName(receiverName)
                .receiverPhoneNumber(this.receiverPhoneNumber)
                .address(this.getAddress())
                .build();
    }
}
