package io.github.amirHFF.exceptions;

import io.github.amirHFF.errorCode.ErrorCode;
import org.springframework.http.HttpStatus;

public class BadInputException extends ManamanRuntimeException {
    public BadInputException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode.getCode());
        setHttpStatus(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public BadInputException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), errorCode.getCode(), cause);
        setHttpStatus(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
