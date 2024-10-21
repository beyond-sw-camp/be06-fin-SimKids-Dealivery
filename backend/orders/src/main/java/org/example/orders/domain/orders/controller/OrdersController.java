package org.example.orders.domain.orders.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.orders.domain.orders.service.OrderService;

import org.example.orders.global.constants.BaseResponse;
import org.example.orders.global.constants.SwaggerDescription;
import org.example.orders.global.constants.SwaggerExamples;

import org.example.orders.domain.orders.model.dto.OrderDto;
import org.springframework.data.domain.Page;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                            schema = @Schema(implementation = OrderDto.OrderRegisterRequest.class),
                            examples = @ExampleObject(value = SwaggerExamples.ORDERS_REGISTER_REQUEST)
                    )
            )
    )
    public ResponseEntity<BaseResponse> registerOrder(
            @RequestHeader("X-User-Idx") Long userIdx,
            @RequestBody OrderDto.OrderRegisterRequest order) {
        OrderDto.OrderCreateResponse res = orderService.register(userIdx, order);
        log.info("[SUCCESS] Order register: [POST][200][Order ID : {}]", res.getOrderIdx());
        return ResponseEntity.ok(new BaseResponse(res));
    }

    @PostMapping("/complete")
    public ResponseEntity<BaseResponse> completeOrder(
            @RequestHeader("X-User-Idx") Long userIdx,
            @RequestBody OrderDto.OrderCompleteRequest order) {

        orderService.complete(userIdx, order);
        log.info("[SUCCESS] Order complete: [POST][200][Order ID : {}]", order.getOrderIdx());
        return ResponseEntity.ok(new BaseResponse());
    }

    @PatchMapping("/{idx}/cancel")
    public ResponseEntity<BaseResponse> cancelOrder(
            @RequestHeader("X-User-Idx") Long userIdx,
            @PathVariable Long idx) {

        orderService.cancel(userIdx, idx);
        return ResponseEntity.ok(new BaseResponse());
    }

    @Operation(summary = "판매자 주문 내역 조회 API")
    @GetMapping(value = "/company/history")
    public BaseResponse companyOrderlist(
            @RequestHeader("X-User-Idx") Long companyIdx,
            Integer page, String status, Integer month) {
        Page<OrderDto.CompanyOrderListResponse> boardListResponses = orderService.companyOrderList(companyIdx, page, status, month);
        return new BaseResponse(boardListResponses);
    }

    @Operation(summary = "판매자 주문 상세 조회 API")
    @GetMapping("/company/{idx}/detail")
    public BaseResponse companyOrderdetail(
            @RequestHeader("X-User-Idx") Long companyIdx,
            @PathVariable Long idx){
        OrderDto.CompanyOrderDetailResponse res = orderService.companyOrderDetail(companyIdx, idx);
        return new BaseResponse(res);
    }

    @Operation(summary = "사용자 주문 내역 조회 API")
    @GetMapping(value = "/user/history")
    public BaseResponse userOrderlist(
            @RequestHeader("X-User-Idx") Long userIdx,
            Integer page, String status, Integer month) {
        Page<OrderDto.UserOrderListResponse> boardListResponses = orderService.userOrderList(userIdx, page, status, month);
        return new BaseResponse(boardListResponses);
    }

    @Operation(summary = "사용자 주문 상세 조회 API")
    @GetMapping("/user/{idx}/detail")
    public BaseResponse userOrderdetail(
            @RequestHeader("X-User-Idx") Long userIdx,
            @PathVariable Long idx){
        OrderDto.UserOrderDetailResponse res = orderService.userOrderDetail(userIdx, idx);
        return new BaseResponse(res);
    }
}
