package org.example.user.domain.user.service;


import lombok.RequiredArgsConstructor;
// import org.example.backend.domain.delivery.repository.DeliveryRepository;
import org.example.user.domain.delivery.repository.DeliveryRepository;
import org.example.user.domain.user.model.dto.UserDto;
import org.example.user.domain.user.model.entity.User;
import org.example.user.domain.user.repository.UserRepository;
import org.example.user.global.adaptor.out.UserKafkaProducer;
import org.example.user.global.common.constants.BaseResponseStatus;
import org.example.user.global.exception.InvalidCustomException;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final DeliveryRepository deliveryRepository;
    private final UserKafkaProducer userKafkaProducer;

    //등록된 계정이 있는지 검사
    public void isExist(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()){
            throw new InvalidCustomException(BaseResponseStatus.USER_SIGNUP_FAIL_ALREADY_EXIST);
        }
    }

    public UserDto.UserDetailResponse getDetail(String email, Long idx){
        User user = userRepository.findByEmailAndIdx(email,idx).orElseThrow(
                () -> new InvalidCustomException(BaseResponseStatus.USER_DETAIL_FAIL_USER_NOT_FOUND)
        );
        return user.toUserDetailResponse();
    }


    @Transactional
    public boolean signup(UserDto.UserSignupRequest request) {
        User newUser = userRepository.save(request.toEntity(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt())));
        deliveryRepository.save(request.toDeliveryEntity(newUser));
        userKafkaProducer.sendSignupMessage(newUser.toUserSignupComplete());
        return true;
    }

    @Transactional
    public boolean socialSignup(UserDto.SocialSignupRequest socialSignupRequest) {
        User newUser = userRepository.save(socialSignupRequest.toEntity());
        deliveryRepository.save(socialSignupRequest.toDeliveryEntity(newUser));
        userKafkaProducer.sendSignupMessage(newUser.toUserSignupComplete());
        return true;
    }

    public void editDetail(Long idx, UserDto.UserDetailEditRequest request) {
        User user = userRepository.findByIdx(idx).orElseThrow(
                () -> new InvalidCustomException(BaseResponseStatus.USER_DETAIL_EDIT_FAIL_USER_NOT_FOUND)
        );
        user.editDetail(request);
        userRepository.save(user);
    }

    public UserDto.AuthUserNotFoundResponse getAuthUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new InvalidCustomException(BaseResponseStatus.USER_DETAIL_FAIL_USER_NOT_FOUND
                ));
        return user.toAuthUserNotFoundResponse();
    }
}
