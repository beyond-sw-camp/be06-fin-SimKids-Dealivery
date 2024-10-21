package org.example.user.domain.delivery.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.user.domain.delivery.model.dto.DeliveryDto;
import org.example.user.domain.delivery.service.DeliveryService;
import org.example.user.global.common.constants.BaseResponse;
import org.example.user.global.common.constants.BaseResponseStatus;
import org.example.user.global.exception.InvalidCustomException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryService deliveryService;

    @GetMapping("/list")
    public BaseResponse getList(
            @RequestHeader("X-User-Idx") Long userIdx
            ){
        List<DeliveryDto.DeliveryResponse> deliveryResponses = deliveryService.getList(userIdx);
        return new BaseResponse(deliveryResponses);
    }

    @PostMapping("")
    public BaseResponse createDelivery(
            @RequestHeader("X-User-Idx") Long userIdx,
            @Valid @RequestBody DeliveryDto.CreateDeliveryRequest request
    ){
        deliveryService.createDelivery(userIdx,request);
        return new BaseResponse();
    }

    @DeleteMapping("/{idx}")
    public BaseResponse deleteDelivery(
            @PathVariable Long idx
    ){
        if (idx == null || idx <= 0){
            throw new InvalidCustomException(BaseResponseStatus.FAIL);
        }
        if(!deliveryService.isExist(idx)){
        throw new InvalidCustomException(BaseResponseStatus.USER_DELIVERY_REMOVE_FAIL_NO_MATCH_DELIVERY);
        }
        deliveryService.deleteDelivery(idx);
        return new BaseResponse();
    }

    @PatchMapping("")
    public BaseResponse setDefault(
            @Valid @RequestBody DeliveryDto.SetDefaultRequest request,
            @RequestHeader("X-User-Idx") Long userIdx
    ){
        if (request.getIdx() == null || request.getIdx() <= 0){
            throw new InvalidCustomException(BaseResponseStatus.FAIL);
        }
        deliveryService.setDefault(request.getIdx(), userIdx);

        return new BaseResponse();
    }

    @PutMapping("")
    public BaseResponse editDelivery(
            @RequestHeader("X-User-Idx") Long userIdx,
            @Valid @RequestBody DeliveryDto.EditDeliveryRequest request
    ){
        deliveryService.editDelivery(userIdx,request);
        return new BaseResponse();
    }

}
