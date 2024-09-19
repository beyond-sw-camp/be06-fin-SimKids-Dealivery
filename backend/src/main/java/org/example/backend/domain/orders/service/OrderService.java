package org.example.backend.domain.orders.service;

import static org.example.backend.domain.orders.model.dto.OrderDto.*;
import static org.example.backend.global.common.constants.BaseResponseStatus.*;

import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.Payment;
import java.io.IOException;
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
import org.example.backend.global.common.constants.OrderStatus;
import org.example.backend.global.exception.InvalidCustomException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final PaymentService paymentService;
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
        });
    }

    @Transactional/*(noRollbackFor = Throwable.class)*/
    public void complete(OrderCompleteRequest request) {

        Orders order = ordersRepository.findById(request.getOrderIdx()).orElseThrow(() -> new InvalidCustomException(
                ORDER_FAIL_NOT_FOUND));
        order.update(request); // 주문 추가 정보 업데이트

        String paymentId = request.getPaymentId();

        try {
            Payment payment = paymentService.getPaymentInfo(paymentId);
            paymentService.validatePayment(payment, order);
            order.setStatus(OrderStatus.ORDER_COMPLETE);

        } catch (IamportResponseException | IOException e) { // 해당하는 결제 정보를 찾지 못했을 때
            //rollbackStock(order);
            order.setStatus(OrderStatus.ORDER_FAIL);
            throw new InvalidCustomException(ORDER_PAYMENT_FAIL);

        } catch (InvalidCustomException e) { // 결제 검증 중 발생한 예외 처리
            //rollbackStock(order);
            order.setStatus(OrderStatus.ORDER_FAIL);
            throw e;
        }
    }

    @Transactional
    public void cancel(Long idx) {
        Orders order = ordersRepository.findById(idx).orElseThrow(() -> new InvalidCustomException(
                ORDER_FAIL_NOT_FOUND));

        if (order.getStatus() !=  OrderStatus.ORDER_COMPLETE) {
            order.setStatus(OrderStatus.ORDER_FAIL); // 사용자가 결제 취소(재고도 줄어들지 않았음)
            return;
        }

        if (order.getStatus() ==  OrderStatus.ORDER_COMPLETE) { //TODO: 주문 완료 & 게시글이 아직 종료되지 않았을 때
            rollbackStock(order);

            String impUid = order.getPaymentId();
            try {
                Payment payment = paymentService.getPaymentInfo(impUid);
                paymentService.refund(impUid, payment);
                order.setStatus(OrderStatus.ORDER_CANCEL);

            } catch (IamportResponseException | IOException  e) {
                throw new InvalidCustomException(ORDER_CANCEL_FAIL);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void rollbackStock(Orders order) {
        List<OrderedProduct> orderedProducts = order.getOrderedProducts();

        orderedProducts.forEach((product) -> {
            Product orderdProduct = productRepository.findByIdWithLock(product.getIdx())
                    .orElseThrow(() -> new InvalidCustomException(ORDER_FAIL_PRODUCT_NOT_FOUND)); // 해당하는 상품을 찾을 수가 없을 때
            orderdProduct.increaseStock(product.getQuantity()); // 재고 수량 변경
        });
    }

}
