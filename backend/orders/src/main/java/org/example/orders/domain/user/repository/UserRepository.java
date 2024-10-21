package org.example.orders.domain.user.repository;

import org.example.orders.domain.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
