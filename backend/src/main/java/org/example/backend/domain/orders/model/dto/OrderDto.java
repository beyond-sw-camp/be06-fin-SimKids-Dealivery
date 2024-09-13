package org.example.backend.domain.orders.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

import lombok.Getter;
import org.example.backend.domain.orders.model.entity.Orders;
import org.example.backend.global.common.constants.OrderStatus;
import org.example.backend.global.utils.RandomCodeGenerator;

public class OrderDto {
    @Builder
    @Getter
    public static class OrderRegisterRequest {
        private Long boardIdx;
        private List<OrderedProductDto.Request> orderedProducts;
        public static Orders toEntity(Long boardIdx) {
            return Orders.builder()
                    .boardIdx(boardIdx)
                    .ordersNumber(RandomCodeGenerator.generate(10))
                    .status(OrderStatus.PAYMENT_WAIT)
                    .build();
        }
    }

    @Builder
    @Getter
    public static class OrderCompleteRequest {
        private Long orderIdx;

        private String receiverName;
        private String receiverPhoneNumber;

        private String address;
        private String addressDetail;
        private String postNumber;

        private String paymentId;
        private String payMethod;
        private Integer usedPoint;
    }

    @Builder
    @Getter
    public static class OrderCreateResponse {
        private Long orderIdx;
    }
}
