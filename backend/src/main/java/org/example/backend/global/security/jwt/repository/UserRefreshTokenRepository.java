package org.example.backend.global.security.jwt.repository;

import org.example.backend.global.security.jwt.model.entity.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken,Long> {
    Optional<UserRefreshToken> findByEmail(String email);

    void deleteByEmail(String email);
}
