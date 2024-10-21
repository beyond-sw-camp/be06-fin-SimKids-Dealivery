package org.example.orders.domain.orders.repository;

import org.example.orders.domain.orders.model.entity.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedProductRepository extends JpaRepository<OrderedProduct, Long> {
}
