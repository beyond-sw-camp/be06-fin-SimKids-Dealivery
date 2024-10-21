package org.example.orders.domain.product.repository;

import jakarta.persistence.LockModeType;
import org.example.orders.domain.product.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Product p WHERE p.idx = :idx")
    Optional<Product> findByIdWithLock(Long idx);
}
