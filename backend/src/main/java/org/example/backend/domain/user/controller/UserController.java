package org.example.backend.domain.user.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.backend.domain.user.model.dto.UserAuthTokenDto;
import org.example.backend.domain.user.model.entity.UserAuthToken;
import org.example.backend.domain.user.service.UserAuthTokenService;
import org.example.backend.domain.user.service.UserService;
import org.example.backend.global.common.BaseResponse;
import org.example.backend.global.common.BaseResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserAuthTokenService userAuthTokenService;

    @Operation(summary = "일반회원가입 API", description = "이메일로 인증번호 전송을 먼저 진행하고 받은 6자리 코드를 입력후에 제출해주세요.")
    @PostMapping("/signup")
    public BaseResponse signup(

    ){
        return new BaseResponse();
    }

    @Operation(summary = "이메일 인증 API", description = "이메일을 입력하면 인증코드를 생성하여 이메일로 발송합니다. 만료기한은 10분입니다.")
    @PostMapping("/email/verify")
    public BaseResponse emailVerify(
            @RequestBody UserAuthTokenDto.UserEmailAuthRequest request
            ){
        if (userAuthTokenService.doAuth(request)){
            return new BaseResponse();
        }
        return new BaseResponse(BaseResponseStatus.FAIL);
    }
}
