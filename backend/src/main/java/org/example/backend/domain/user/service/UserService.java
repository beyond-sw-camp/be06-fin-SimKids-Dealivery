package org.example.backend.domain.user.service;


import lombok.RequiredArgsConstructor;
import org.example.backend.domain.user.repository.DeliveryRepository;
import org.example.backend.domain.user.repository.UserAuthTokenRepository;
import org.example.backend.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserAuthTokenRepository userAuthTokenRepository;
    private final DeliveryRepository deliveryRepository;
}
