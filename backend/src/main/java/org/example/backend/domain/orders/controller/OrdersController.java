package org.example.backend.domain.orders.controller;

import static org.example.backend.domain.orders.model.dto.OrderDto.*;

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
import org.springframework.http.ResponseEntity;
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
        OrderCreateResponse res = orderService.register(order);
        return ResponseEntity.ok(new BaseResponse(res));
    }
}
