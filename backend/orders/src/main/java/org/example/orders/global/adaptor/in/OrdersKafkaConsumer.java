package org.example.orders.global.adaptor.in;

import lombok.*;
import org.example.orders.domain.board.model.entity.ProductBoard;
import org.example.orders.domain.board.repository.ProductBoardRepository;
import org.example.orders.domain.company.model.entity.Company;
import org.example.orders.domain.company.model.event.CompanyEvent;
import org.example.orders.domain.company.repository.CompanyRepository;
import org.example.orders.domain.orders.model.event.OrdersEvent;
import org.example.orders.domain.product.model.dto.ProductDto;
import org.example.orders.domain.product.model.entity.Product;
import org.example.orders.domain.product.repository.ProductRepository;
import org.example.orders.domain.user.event.UserEvent;
import org.example.orders.domain.user.repository.UserRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.example.orders.domain.user.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrdersKafkaConsumer {

    private final ProductBoardRepository productBoardRepository;
    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    @KafkaListener(topics = "board-register", groupId = "orders_group")
    @Transactional
    public void consumeBoardRegisterEvent(OrdersEvent.BoardRegisterEvent event) {
        System.out.println("메시지 받음");
        Company company = companyRepository.findById(event.getCompanyIdx()).orElseThrow();
        ProductBoard productBoard = productBoardRepository.save(event.toEntity(company));
        productRepository.saveAll(
                event.getProducts().stream()
                        .map(registeredProduct -> registeredProduct.toEntity(productBoard)) // Product 엔티티로 변환
                        .collect(Collectors.toList()) // List<Product>로 수집
        );
    }
  
    @KafkaListener(topics = "user_signup_complete", groupId = "orders_group")
    public void user_signup_complete(UserEvent.UserSignupCompleteEvent userSignupCompleteEvent) {
        User newUser = userSignupCompleteEvent.toEntity();
        userRepository.save(newUser);
    }

    @KafkaListener(topics = "company_signup_complete", groupId = "orders_group")
    public void company_signup_complete(CompanyEvent.CompanySignupCompleteEvent companySignupCompleteEvent) {
        Company newCompany = companySignupCompleteEvent.toEntity();
        companyRepository.save(newCompany);
    }
}
