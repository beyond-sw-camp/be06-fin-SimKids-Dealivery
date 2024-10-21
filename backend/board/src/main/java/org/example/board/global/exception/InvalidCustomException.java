package org.example.board.global.exception;

import lombok.Getter;
import org.example.board.global.common.constants.BaseResponseStatus;


@Getter
public class InvalidCustomException extends RuntimeException {
    private final BaseResponseStatus status;

    public InvalidCustomException(BaseResponseStatus status) {
        this.status = status;
    }
}
