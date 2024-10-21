package org.example.user.domain.company.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.user.domain.company.model.dto.CompanyDto;
import org.example.user.domain.company.model.entity.Company;
import org.example.user.domain.company.repository.CompanyRepository;
import org.example.user.global.adaptor.out.CompanyKafkaProducer;
import org.example.user.global.common.constants.BaseResponseStatus;
import org.example.user.global.exception.InvalidCustomException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyKafkaProducer companyKafkaProducer;

    public Boolean isExist(String email){
        Optional<Company> optionalCompany = companyRepository.findByEmail(email);
        if (optionalCompany.isPresent()){
            throw new InvalidCustomException(BaseResponseStatus.USER_SIGNUP_FAIL_ALREADY_EXIST);
        }

        return true;
    }

    @Transactional
    public boolean signup(CompanyDto.CompanySignupRequest request) {
        Company newCompany = companyRepository.save(request.toEntity(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt())));

        companyKafkaProducer.sendSignupMessage(newCompany.toCompanySignupComplete());
        return true;
    }

    public CompanyDto.BoardCompanyNotFoundResponse getCurrentCompany(Long companyIdx) {
        Company company = companyRepository.findById(companyIdx).orElseThrow(
                ()-> new InvalidCustomException(BaseResponseStatus.USER_DETAIL_FAIL_USER_NOT_FOUND
                ));
        return company.toCompanyNotFoundResponse();
    }

    public CompanyDto.AuthCompanyNotFoundResponse getAuthCompany(String email) {
        Company company = companyRepository.findByEmail(email).orElseThrow(
                ()-> new InvalidCustomException(BaseResponseStatus.USER_DETAIL_FAIL_USER_NOT_FOUND
                ));
        return company.toAuthCompanyNotFoundResponse();
    }

}