package io.github.amirHFF.exceptions;

import io.github.amirHFF.errorCode.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundException extends ManamanRuntimeException {

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode.getCode());
        setHttpStatus(HttpStatus.NOT_FOUND);
    }

    public NotFoundException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), errorCode.getCode(), cause);
        setHttpStatus(HttpStatus.NOT_FOUND);
    }
}
