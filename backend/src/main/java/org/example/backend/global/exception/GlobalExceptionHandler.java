package org.example.backend.global.exception;


import org.example.backend.global.common.constants.BaseResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
