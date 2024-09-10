package org.example.backend.global.exception;


import jakarta.validation.ConstraintViolationException;
import org.example.backend.global.common.BaseResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Order(value = 1)
public class GlobalExceptionHandler {


    //커스텀 예외 핸들러(BaseResponse 기반)
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<String> handleGlobalException(InvalidCustomException e) {
        return new BaseResponse<>(e.getStatus());
    }

    //@Valid 실패시 예외 핸들러
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity
                .badRequest().body(new BaseResponse("잘못된 요청입니다."));

    }
}
