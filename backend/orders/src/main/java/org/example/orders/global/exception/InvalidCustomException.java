package org.example.orders.global.exception;

import lombok.Getter;
import org.example.orders.global.constants.BaseResponseStatus;


@Getter
public class InvalidCustomException extends RuntimeException {
    private final BaseResponseStatus status;

    public InvalidCustomException(BaseResponseStatus status) {
        this.status = status;
    }
}
