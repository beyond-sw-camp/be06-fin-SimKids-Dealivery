package org.example.backend.domain.delivery.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.domain.delivery.model.dto.DeliveryDto;
import org.example.backend.domain.delivery.model.entity.Delivery;
import org.example.backend.domain.delivery.repository.DeliveryRepository;
import org.example.backend.domain.user.model.entity.User;
import org.example.backend.domain.user.repository.UserRepository;
import org.example.backend.global.common.constants.BaseResponseStatus;
import org.example.backend.global.exception.InvalidCustomException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final UserRepository userRepository;


    public List<DeliveryDto.DeliveryResponse> getList(Long idx) {
        List<Delivery> deliveries = deliveryRepository.findAllByUserIdx(idx).orElseThrow(
                () -> new InvalidCustomException(BaseResponseStatus.USER_DELIVERY_LIST_FAIL)
        );

        return deliveries.stream().map(Delivery::toDeliveryResponse).toList();
    }

    @Transactional
    public void createDelivery(Long idx, DeliveryDto.CreateDeliveryRequest request) {
        //user 객체 조회
        User user = userRepository.findByIdx(idx).orElseThrow(
                () -> new InvalidCustomException(BaseResponseStatus.USER_DETAIL_FAIL_USER_NOT_FOUND)
        );
        //배송지 숫자가 10개 미만인지 체크
        if (user.getDeliveries().size() >= 10){
            throw new InvalidCustomException(BaseResponseStatus.USER_DELIVERY_ADD_FAIL_EXCEEDANCE_MAX_DELIVERY);
        }
        //요청으로 들어온 기본배송지 설정이 true인 경우 기존에 설정된 기본배송지 false로 변경
        if (request.getIsDefault()){
            if (isDefaultExist(user.getDeliveries())){
                Delivery defaultDelivery = getDefaultDelivery(user.getDeliveries());
                //기존 기본배송지 false로 업데이트
                deliveryRepository.save(defaultDelivery);
            }
        }
        //요청으로 들어온 배송지 정보 저장
        deliveryRepository.save(request.toEntity(user));
    }

    //기존에 isDefault가 true인 배송지 존재유무 반환
    public boolean isDefaultExist(List<Delivery> deliveries) {
        return deliveries.stream()
                .anyMatch(Delivery::getIsDefault);
    }
    public Delivery getDefaultDelivery(List<Delivery> deliveries) {
        //받아온 리스트에서 isDefault가 true인 객체의 해당 값을 true로 바꾸고 반환
        return deliveries.stream()
                .filter(Delivery::getIsDefault)
                .findFirst()
                .map(delivery -> {
                    delivery.setIsDefault(false);
                    return delivery;
                })
                .orElse(null);
    }

    public void deleteDelivery(Long idx) {
        deliveryRepository.deleteById(idx);
    }

    public boolean isExist(Long idx) {
        Optional<Delivery> optionalDelivery = deliveryRepository.findByIdx(idx);
        if (optionalDelivery.isEmpty()){
            return false;
        }
        return true;
    }
}
