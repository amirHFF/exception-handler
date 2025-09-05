package io.github.amirHFF.exceptions;

import io.github.amirHFF.errorCode.ErrorCode;
import org.springframework.http.HttpStatus;

public class BusinessException extends ManamanRuntimeException {

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode.getCode());
        setHttpStatus(HttpStatus.BAD_REQUEST);
    }

    public BusinessException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), errorCode.getCode(), cause);
        setHttpStatus(HttpStatus.BAD_REQUEST);
    }
}
