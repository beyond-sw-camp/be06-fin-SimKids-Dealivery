package org.example.backend.global.security.jwt.repository;

import org.example.backend.global.security.jwt.model.entity.CompanyRefreshToken;
import org.example.backend.global.security.jwt.model.entity.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepositoryTokenRepository extends JpaRepository<CompanyRefreshToken,Long> {
}
