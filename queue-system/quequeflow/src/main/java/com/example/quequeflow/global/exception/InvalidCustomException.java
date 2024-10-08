package com.example.quequeflow.global.exception;

import com.example.quequeflow.global.common.constants.BaseResponseStatus;

import lombok.Getter;


@Getter
public class InvalidCustomException extends RuntimeException {
    private final BaseResponseStatus status;

    public InvalidCustomException(BaseResponseStatus status) {
        this.status = status;
    }
}
