package org.example.backend.domain.orders.service;

import static org.example.backend.domain.orders.model.dto.OrderDto.*;
import static org.example.backend.global.common.constants.BaseResponseStatus.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backend.domain.board.model.entity.ProductBoard;
import org.example.backend.domain.board.product.model.entity.Product;
import org.example.backend.domain.board.product.repository.ProductRepository;
import org.example.backend.domain.board.repository.ProductBoardRepository;
import org.example.backend.domain.orders.model.dto.OrderedProductDto;
import org.example.backend.domain.orders.model.entity.OrderedProduct;
import org.example.backend.domain.orders.model.entity.Orders;
import org.example.backend.domain.orders.repository.OrderedProductRepository;
import org.example.backend.domain.orders.repository.OrdersRepository;
import org.example.backend.global.exception.InvalidCustomException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;
    private final OrderedProductRepository orderedProductRepository;
    private final ProductRepository productRepository;
    private final ProductBoardRepository productBoardRepository;

    @Transactional
    public OrderCreateResponse register(OrderRegisterRequest request) {

        validateOrder(request);

        Orders order = OrderRegisterRequest.toEntity(request.getBoardIdx());
        ordersRepository.save(order);

        List<OrderedProduct> orderedProducts = request.getOrderedProducts().stream()
                .map(product -> OrderedProductDto.Request.toEntity(product, order))
                .collect(Collectors.toList());

        orderedProductRepository.saveAll(orderedProducts);

        return OrderCreateResponse.builder()
                .orderIdx(order.getIdx())
                .build();
    }

    public void validateOrder(OrderRegisterRequest order){

        ProductBoard board = productBoardRepository.findById(order.getBoardIdx())
                .orElseThrow(() -> new InvalidCustomException(ORDER_FAIL_EVENT_NOT_FOUND));

        if (board.getEndedAt().isBefore(LocalDateTime.now())) {
            throw new InvalidCustomException(ORDER_FAIL_EXPIRED_EVENT); // 이벤트가 끝났을 때
        }

        order.getOrderedProducts().forEach((product) -> {
            Product orderdProduct = productRepository.findByIdWithLock(product.getIdx())
                    .orElseThrow(() -> new InvalidCustomException(ORDER_FAIL_PRODUCT_NOT_FOUND)); // 해당하는 상품을 찾을 수가 없을 때

            if (product.getQuantity() > orderdProduct.getStock()) {
                throw new InvalidCustomException(ORDER_CREATE_FAIL_LACK_STOCK); // 재고 수량 없을 때
            }

            orderdProduct.decreaseStock(product.getQuantity()); // 재고 수량 변경
        });
    }
}
