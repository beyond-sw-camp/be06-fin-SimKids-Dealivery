package org.example.orders.domain.company.repository;

import org.example.orders.domain.company.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
