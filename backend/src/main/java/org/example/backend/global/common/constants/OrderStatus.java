package org.example.backend.global.common.constants;

public enum OrderStatus {

    PAYMENT_WAIT("결제 대기"),
    ORDER_COMPLETE("주문 완료"),
    ORDER_FAIL("주문 실패"),
    ORDER_CANCEL("주문 취소");
    private final String status;
    OrderStatus(final String status) {
        this.status = status;
    }
}
