package org.example.backend.domain.orders.controller;

import static org.example.backend.domain.orders.model.dto.OrderDto.*;
import static org.example.backend.global.common.constants.BaseResponseStatus.ORDER_PAYMENT_FAIL;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backend.domain.orders.service.OrderService;

import org.example.backend.global.common.constants.BaseResponse;
import org.example.backend.global.common.constants.SwaggerDescription;
import org.example.backend.global.common.constants.SwaggerExamples;
import org.example.backend.global.exception.InvalidCustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrderService orderService;

    @PostMapping("/register")
    @Operation(summary = "주문 등록 API", description = SwaggerDescription.ORDERS_REGISTER_REQUEST,
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OrderRegisterRequest.class),
                            examples = @ExampleObject(value = SwaggerExamples.ORDERS_REGISTER_REQUEST)
                    )
            )
    )
    public ResponseEntity<BaseResponse> registerOrder(/*@AuthenticationPrincipal CustomUserDetails customUserDetails,*/ @RequestBody OrderRegisterRequest order) {
        // TODO: 로그인한 사용자(주문자) 정보 저장
        OrderCreateResponse res = orderService.register(order);
        log.info("[SUCCESS] Order register: [POST][200][Order ID : {}]", res.getOrderIdx());
        return ResponseEntity.ok(new BaseResponse(res));
    }

    @PostMapping("/complete")
    public ResponseEntity<BaseResponse> completeOrder(@RequestBody OrderCompleteRequest order) {
        orderService.complete(order);
        log.info("[SUCCESS] Order complete: [POST][200][Order ID : {}]", order.getOrderIdx());
        return ResponseEntity.ok(new BaseResponse());
    }

    @PatchMapping("/{idx}/cancel")
    public ResponseEntity<BaseResponse> cancelOrder(@PathVariable Long idx) {
        orderService.cancel(idx);
        return ResponseEntity.ok(new BaseResponse());
    }

}
