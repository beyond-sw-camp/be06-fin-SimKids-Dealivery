package org.example.backend.domain.user.service;


import lombok.RequiredArgsConstructor;
import org.example.backend.domain.user.model.dto.UserDto;
import org.example.backend.domain.user.model.entity.User;
import org.example.backend.domain.user.repository.DeliveryRepository;
import org.example.backend.domain.user.repository.UserAuthTokenRepository;
import org.example.backend.domain.user.repository.UserRepository;
import org.example.backend.global.common.constants.BaseResponseStatus;
import org.example.backend.global.exception.InvalidCustomException;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final DeliveryRepository deliveryRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //등록된 계정이 있는지 검사
    public void isExist(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()){
            throw new InvalidCustomException(BaseResponseStatus.USER_SIGNUP_FAIL_ALREADY_EXIST);
        }
    }
    public boolean signup(UserDto.UserSignupRequest request) {
        User newUser = userRepository.save(request.toEntity(passwordEncoder.encode(request.getPassword())));
        //회원가입시 입력한 주소를 기본배송지로 배송지목록에 추가

        return true;
    }


}