package org.example.backend.domain.board.product.repository;

import org.example.backend.domain.board.product.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
