package org.example.user.domain.company.repository;

import org.example.user.domain.company.model.entity.CompanyRegisterVerify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRegisterVerifyRepository extends JpaRepository<CompanyRegisterVerify, Long> {
    void deleteByRegNumber(String regNumber);
}
