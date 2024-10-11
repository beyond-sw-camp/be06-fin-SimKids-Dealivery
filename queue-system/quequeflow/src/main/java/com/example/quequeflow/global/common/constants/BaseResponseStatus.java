package com.example.quequeflow.global.common.constants;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum BaseResponseStatus {
    // 모든 요청 성공 1000
    SUCCESS(true, 1000, "요청이 성공하였습니다."),
    // 모든 요청 실패 9000
    FAIL(false, 9000, "요청에 실패했습니다. 관리자에게 문의해주세요."),
    FAIL_UNAUTHORIZED(false, 9001, "요청이 실패했습니다. 권한이 없습니다."),

    QUEUE_REQUIRED(false, 9100, "[쿠키값 X] 대기열 등록이 필요합니다."),
    // 큐에 이미 등록됨
    QUEUE_ALREADY_REGISTERED_USER(false, 9200, "이미 대기열에 등록되어 있습니다"),

    WAITING_IN_QUEUE(false,9300, "대기열에 있습니다. 대기 중입니다.");


    private boolean isSuccess;
    private Integer code;
    private String message;

    BaseResponseStatus(Boolean isSuccess, Integer code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
