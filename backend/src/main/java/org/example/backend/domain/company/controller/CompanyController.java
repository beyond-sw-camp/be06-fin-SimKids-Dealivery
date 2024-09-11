package org.example.backend.domain.company.controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.backend.domain.company.model.dto.CompanyAuthTokenDto;
import org.example.backend.domain.company.service.CompanyAuthTokenService;
import org.example.backend.global.common.constants.BaseResponse;
import org.example.backend.global.common.constants.BaseResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyAuthTokenService companyAuthTokenService;

    @Operation(summary = "업체 이메일 인증 API", description = "이메일을 입력하면 인증코드를 생성하여 이메일로 발송합니다. 만료기한은 10분입니다.")
    @PostMapping("/email/verify")
    public BaseResponse emailVerify(
            @Valid @RequestBody CompanyAuthTokenDto.CompanyEmailAuthRequest request
            ){
        if (companyAuthTokenService.doAuth(request)){
            return new BaseResponse();
        }
        return new BaseResponse(BaseResponseStatus.FAIL);
    }
}
