package org.example.orders.domain.orders.service;

import static org.example.orders.global.constants.BaseResponseStatus.*;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.Payment;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.orders.domain.board.model.entity.ProductBoard;
import org.example.orders.domain.company.repository.CompanyRepository;
import org.example.orders.domain.product.model.dto.ProductDto;
import org.example.orders.domain.product.model.entity.Product;
import org.example.orders.domain.product.repository.ProductRepository;
import org.example.orders.domain.board.repository.ProductBoardRepository;
import org.example.orders.domain.company.model.entity.Company;
import org.example.orders.domain.orders.model.dto.OrderedProductDto;
import org.example.orders.domain.orders.model.entity.OrderedProduct;
import org.example.orders.domain.orders.model.entity.Orders;
import org.example.orders.domain.orders.repository.OrderedProductRepository;
import org.example.orders.domain.orders.repository.OrdersRepository;
import org.example.orders.domain.user.model.entity.User;
import org.example.orders.domain.user.repository.UserRepository;
import org.example.orders.global.adaptor.out.BoardServiceClient;
import org.example.orders.global.adaptor.out.UserServiceClient;
import org.example.orders.global.constants.BaseResponseStatus;
import org.example.orders.global.constants.OrderStatus;
import org.example.orders.global.exception.InvalidCustomException;
import org.example.orders.domain.orders.model.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final int COMPANY_PAGE_SIZE = 5;
    private final int USER_PAGE_SIZE = 3;
    private final PaymentService paymentService;
    private final OrderQueueService orderQueueService;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final OrdersRepository ordersRepository;
    private final OrderedProductRepository orderedProductRepository;
    private final ProductRepository productRepository;
    private final ProductBoardRepository productBoardRepository;
    private final BoardServiceClient boardServiceClient;

    @Transactional
    public OrderDto.OrderCreateResponse register(Long userIdx, OrderDto.OrderRegisterRequest request) {

        validateOrder(request, userIdx);
        User user = validAndGetUser(userIdx);
        Orders order = OrderDto.OrderRegisterRequest.toEntity(request.getBoardIdx(), user);
        ordersRepository.save(order);

        List<OrderedProduct> orderedProducts = request.getOrderedProducts().stream()
                .map(product -> OrderedProductDto.Request.toEntity(product, order))
                .collect(Collectors.toList());

        orderedProductRepository.saveAll(orderedProducts);

        return OrderDto.OrderCreateResponse.builder()
                .orderIdx(order.getIdx())
                .build();
    }

    public void validateOrder(OrderDto.OrderRegisterRequest order, Long userIdx){

        ProductBoard board = productBoardRepository.findById(order.getBoardIdx())
                .orElseThrow(() -> {
                    orderQueueService.exitQueue(order.getBoardIdx(), userIdx);
                    return new InvalidCustomException(ORDER_FAIL_EVENT_NOT_FOUND);
                });

        if (board.getEndedAt().isBefore(LocalDateTime.now())) {
            throw new InvalidCustomException(ORDER_FAIL_EXPIRED_EVENT); // 이벤트가 끝났을 때
        }

        order.getOrderedProducts().forEach((product) -> {
            Product orderdProduct = productRepository.findByIdWithLock(product.getIdx())
                    .orElseThrow(() -> {
                        orderQueueService.exitQueue(order.getBoardIdx(), userIdx);
                        return new InvalidCustomException(ORDER_FAIL_PRODUCT_NOT_FOUND); // 해당하는 상품을 찾을 수가 없을 때
                    });

            if (product.getQuantity() > orderdProduct.getStock()) {
                orderQueueService.exitQueue(order.getBoardIdx(), userIdx);
                throw new InvalidCustomException(ORDER_CREATE_FAIL_LACK_STOCK); // 재고 수량 없을 때
            }
        });
    }

    public void complete(Long userIdx, OrderDto.OrderCompleteRequest request) {

        Orders order = ordersRepository.findById(request.getOrderIdx()).orElseThrow(() -> new InvalidCustomException(
                ORDER_FAIL_NOT_FOUND));
        User user = validAndGetUser(userIdx);
        if (order.getUser().getIdx() != user.getIdx()) {
            throw new InvalidCustomException(ORDER_PAYMENT_FAIL);
        }

        order.update(request); // 주문 추가 정보 업데이트
        ordersRepository.save(order);

        String paymentId = request.getPaymentId();

        try {
            Payment payment = paymentService.getPaymentInfo(paymentId);
            paymentService.validatePayment(payment, order);
            order.getUser().deductPoints(order.getUsedPoint());
            order.setStatus(OrderStatus.ORDER_COMPLETE);
            ordersRepository.save(order);
            orderQueueService.exitQueue(order.getBoardIdx(), user.getIdx());


        } catch (IamportResponseException | IOException e) { // 해당하는 결제 정보를 찾지 못했을 때
            order.setStatus(OrderStatus.ORDER_FAIL);
            ordersRepository.save(order);
            orderQueueService.exitQueue(order.getBoardIdx(), user.getIdx());
            throw new InvalidCustomException(ORDER_PAYMENT_FAIL);

        } catch (InvalidCustomException e) { // 결제 검증 중 발생한 예외 처리
            order.setStatus(OrderStatus.ORDER_FAIL);
            ordersRepository.save(order);
            orderQueueService.exitQueue(order.getBoardIdx(), user.getIdx());
            throw e;
        }

    }

    @Transactional
    public void cancel(Long userIdx, Long idx) {
        Orders order = ordersRepository.findById(idx).orElseThrow(() -> new InvalidCustomException(
                BaseResponseStatus.ORDER_FAIL_NOT_FOUND));
        User user = validAndGetUser(userIdx);
        if (order.getUser().getIdx() != user.getIdx())  { // 해당하는 사용자의 주문이 아닐 때
            throw new InvalidCustomException(ORDER_CANCEL_FAIL);
        }

        if (order.getStatus() !=  OrderStatus.ORDER_COMPLETE) {
            ordersRepository.delete(order);
            orderQueueService.exitQueue(order.getBoardIdx(), user.getIdx());
            return;
        }

        ProductBoard board = productBoardRepository.findById(order.getBoardIdx()).orElseThrow(() -> new InvalidCustomException(ORDER_CANCEL_FAIL));

        if (order.getStatus() ==  OrderStatus.ORDER_COMPLETE && board.getEndedAt().isAfter(LocalDateTime.now())) {
            rollbackStock(order);
            String impUid = order.getPaymentId();
            try {
                Payment payment = paymentService.getPaymentInfo(impUid);
                paymentService.refund(impUid, payment);
                order.getUser().earnPoints(order.getUsedPoint());

                order.setStatus(OrderStatus.ORDER_CANCEL);

            } catch (IamportResponseException | IOException  e) {
                throw new InvalidCustomException(ORDER_CANCEL_FAIL);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void rollbackStock(Orders order) {
        List<OrderedProduct> orderedProducts = order.getOrderedProducts();

        orderedProducts.forEach((orderedProduct) -> {
            Product product = productRepository.findByIdWithLock(orderedProduct.getProductIdx())
                    .orElseThrow(() -> new InvalidCustomException(BaseResponseStatus.ORDER_FAIL_PRODUCT_NOT_FOUND));
            product.increaseStock(orderedProduct.getQuantity());
        });

        List<ProductDto.OrderedProductInfo> orderedProductInfoList = orderedProducts.stream()
                .map(OrderedProduct::toOrderedProductInfo)
                .collect(Collectors.toList());
        if (!boardServiceClient.isStockRestored(orderedProductInfoList)){
            throw new InvalidCustomException(ORDER_CANCEL_FAIL);
        }
    }

    public Page<OrderDto.CompanyOrderListResponse> companyOrderList(Long companyIdx, Integer page, String status, Integer month) {
        Pageable pageable = PageRequest.of(page - 1, COMPANY_PAGE_SIZE, Sort.Direction.DESC, "idx");
        Company company = validAndGetCompany(companyIdx);
        Page<Orders> orders = ordersRepository.historyWithPaging(company, pageable, status, month);
        return orders.map(order -> {
            String title = productBoardRepository.findById(order.getBoardIdx())
                    .orElseThrow(() -> new InvalidCustomException(BaseResponseStatus.ORDER_FAIL_EVENT_NOT_FOUND)).getTitle();
            return order.toCompanyOrderListResponse(title);
        });
    }


    public OrderDto.CompanyOrderDetailResponse companyOrderDetail(Long companyIdx, Long orderIdx) {
        Orders order = ordersRepository.findById(orderIdx)
                .orElseThrow(() -> new InvalidCustomException(BaseResponseStatus.ORDER_FAIL_DETAIL));
        Company company = validAndGetCompany(companyIdx);
        ProductBoard board = productBoardRepository.findById(order.getBoardIdx())
                .orElseThrow(() -> new InvalidCustomException(BaseResponseStatus.ORDER_FAIL_EVENT_NOT_FOUND));

        if (company.getIdx() != board.getCompany().getIdx()) {
            throw new InvalidCustomException(BaseResponseStatus.ORDER_FAIL_DETAIL);
        }

        List<OrderedProduct> orederdProducts = order.getOrderedProducts();
        List<OrderedProductDto.OrderedProductResponse> products = orederdProducts.stream().map(orderdProduct ->{
            Product product = productRepository.findById(orderdProduct.getProductIdx()).orElseThrow();
            return orderdProduct.toOrderedProductResponse(product, board.getDiscountRate());
        }
        ).collect(Collectors.toList());

        return order.toCompanyOrderDetailResponse(products);
    }

    public Page<OrderDto.UserOrderListResponse> userOrderList(Long userIdx, Integer page, String status, Integer month) {
        Pageable pageable = PageRequest.of(page - 1, USER_PAGE_SIZE, Sort.Direction.DESC, "idx");
        User user = validAndGetUser(userIdx);
        Page<Orders> orders = ordersRepository.historyWithPaging(user, pageable, status, month);
        return orders.map(order -> {
            ProductBoard board = productBoardRepository.findById(order.getBoardIdx())
                    .orElseThrow(() -> new InvalidCustomException(BaseResponseStatus.ORDER_FAIL_EVENT_NOT_FOUND));
            return order.toUserOrderListResponse(board);
        });
    }

    public OrderDto.UserOrderDetailResponse userOrderDetail(Long userIdx, Long orderIdx) {
        Orders order = ordersRepository.findById(orderIdx)
                .orElseThrow(() -> new InvalidCustomException(BaseResponseStatus.ORDER_FAIL_DETAIL));
        User user = validAndGetUser(userIdx);
        if (user.getIdx() != order.getUser().getIdx()) {
            throw new InvalidCustomException(BaseResponseStatus.ORDER_FAIL_DETAIL);
        }

        ProductBoard board = productBoardRepository.findById(order.getBoardIdx())
                .orElseThrow(() -> new InvalidCustomException(BaseResponseStatus.ORDER_FAIL_EVENT_NOT_FOUND));

        return order.toUserOrderDetailResponse(board);
    }

    private User validAndGetUser(Long userIdx) {
            User user = userRepository.findById(userIdx).orElseThrow(
                    ()-> new InvalidCustomException(USER_DETAIL_FAIL_USER_NOT_FOUND)
            );
            return user;
        }

    private Company validAndGetCompany(Long companyIdx) {
        Company company = companyRepository.findById(companyIdx).orElseThrow(
                () -> new InvalidCustomException(USER_DETAIL_FAIL_USER_NOT_FOUND)
        );
        return company;
    }

}
