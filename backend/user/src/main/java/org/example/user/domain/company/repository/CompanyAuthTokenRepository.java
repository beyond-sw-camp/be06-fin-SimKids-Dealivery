package org.example.user.domain.company.repository;

import org.example.user.domain.company.model.entity.CompanyAuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyAuthTokenRepository extends JpaRepository<CompanyAuthToken, Long> {
    void deleteAllByEmail(String email);

    Optional<CompanyAuthToken> findByEmail(String email);
}
