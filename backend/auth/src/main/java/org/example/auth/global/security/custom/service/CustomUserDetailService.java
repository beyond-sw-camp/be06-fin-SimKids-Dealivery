package org.example.auth.global.security.custom.service;

import lombok.RequiredArgsConstructor;
import org.example.auth.domain.company.model.entity.Company;
import org.example.auth.domain.user.repository.UserRepository;
import org.example.auth.global.adaptor.out.CompanyServiceClient;
import org.example.auth.global.adaptor.out.UserServiceClient;
import org.example.auth.global.security.custom.model.dto.CustomCompanyDetails;
import org.example.auth.global.security.custom.model.dto.CustomUserDetails;
import org.example.auth.domain.company.repository.CompanyRepository;
import org.example.auth.domain.user.model.entity.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final UserServiceClient userServiceClient;
    private final CompanyServiceClient companyServiceClient;

    @Override
    public UserDetails loadUserByUsername(String request) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails loadUserByUsername(String email ,String type) throws UsernameNotFoundException {
        if (type.equals("user")){
            try {
                User user = userRepository.findByEmail(email).orElseThrow();
                if (!user.getType().equals("inapp")){
                    throw new UsernameNotFoundException(email);
                }
                return new CustomUserDetails(user);
            } catch (Exception e){
                User user = userServiceClient.getUserByEmail(email);
                userRepository.save(user);
                if (user == null){
                    throw new UsernameNotFoundException(email);
                }
                return new CustomUserDetails(user);
            }
        }

        if (type.equals("company")){
            try {
                Company company = companyRepository.findByEmail(email).orElseThrow();
                return new CustomCompanyDetails(company);
            } catch (Exception e){
                Company company = companyServiceClient.getCompanyByEmail(email);
                companyRepository.save(company);
                if (company == null){
                    throw new UsernameNotFoundException(email);
                }
                return new CustomCompanyDetails(company);
            }
        }

        throw new UsernameNotFoundException(email);
    }
}
