package com.example.quequeflow.global.exception;


import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.quequeflow.global.common.constants.BaseResponse;

@RestControllerAdvice
@Order(value = 1)
public class GlobalExceptionHandler {

    //커스텀 예외 핸들러(BaseResponse 기반)
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<String> handleGlobalException(InvalidCustomException e) {
        return new BaseResponse<>(e.getStatus());
    }
}
