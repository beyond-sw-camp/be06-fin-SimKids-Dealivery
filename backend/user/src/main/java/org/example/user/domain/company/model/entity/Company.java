package org.example.user.domain.company.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.user.domain.company.model.dto.CompanyDto;

import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String phoneNumber;

    private Boolean status;

    private String postNumber;

    private String address;

    private String addressDetail;

    private Boolean emailStatus;

    private LocalDateTime registeredAt;

    private String role;

    @Column(unique = true)
    private String companyName;

    @Column(unique = true)
    private String regNumber;

    private String openedAt;

    private String mosNumber;

    private Boolean regStatus;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
//    private List<ProductBoard> productBoards = new ArrayList<>();

    public CompanyDto.CompanySignupComplete toCompanySignupComplete(){
        return CompanyDto.CompanySignupComplete.builder()
                .idx(this.idx)
                .companyName(this.companyName)
                .email(this.email)
                .password(this.password)
                .role(this.role)
                .build();
    }

    public CompanyDto.BoardCompanyNotFoundResponse toCompanyNotFoundResponse(){
        return CompanyDto.BoardCompanyNotFoundResponse.builder()
                .idx(this.idx)
                .companyName(this.companyName)
                .email(this.email)
                .build();
    }

    public CompanyDto.AuthCompanyNotFoundResponse toAuthCompanyNotFoundResponse(){
        return CompanyDto.AuthCompanyNotFoundResponse.builder()
                .idx(this.idx)
                .email(this.email)
                .password(this.password)
                .role(this.role)
                .build();
    }
}
