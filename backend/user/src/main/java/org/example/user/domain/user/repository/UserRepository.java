package org.example.user.domain.user.repository;

import org.example.user.domain.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndIdx(String email, Long idx);

    Optional<User> findByIdx(Long idx);
}
