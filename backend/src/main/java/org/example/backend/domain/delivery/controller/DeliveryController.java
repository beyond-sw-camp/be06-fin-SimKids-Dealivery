package org.example.backend.domain.delivery.controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.domain.delivery.model.dto.DeliveryDto;
import org.example.backend.domain.delivery.service.DeliveryService;
import org.example.backend.global.common.constants.BaseResponse;
import org.example.backend.global.common.constants.BaseResponseStatus;
import org.example.backend.global.exception.InvalidCustomException;
import org.example.backend.global.security.custom.model.dto.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryService deliveryService;

    @GetMapping("/list")
    public BaseResponse getList(
            @AuthenticationPrincipal CustomUserDetails userDetails
            ){
        List<DeliveryDto.DeliveryResponse> deliveryResponses = deliveryService.getList(userDetails.getIdx());
        return new BaseResponse(deliveryResponses);
    }

    @PostMapping("")
    public BaseResponse createDelivery(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody DeliveryDto.CreateDeliveryRequest request
    ){
        deliveryService.createDelivery(userDetails.getIdx(),request);
        return new BaseResponse();
    }

    @DeleteMapping("/{idx}")
    public BaseResponse deleteDelivery(
            @PathVariable Long idx
    ){
        if(!deliveryService.isExist(idx)){
        throw new InvalidCustomException(BaseResponseStatus.USER_DELIVERY_REMOVE_FAIL_NO_MATCH_DELIVERY);
        }
        deliveryService.deleteDelivery(idx);
        return new BaseResponse();
    }

}
