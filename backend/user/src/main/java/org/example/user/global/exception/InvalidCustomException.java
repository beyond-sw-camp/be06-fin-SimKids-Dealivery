package org.example.user.global.exception;

import lombok.Getter;
import org.example.user.global.common.constants.BaseResponseStatus;


@Getter
public class InvalidCustomException extends RuntimeException {
    private final BaseResponseStatus status;

    public InvalidCustomException(BaseResponseStatus status) {
        this.status = status;
    }
}
