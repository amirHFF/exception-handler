package io.github.amirHFF.exceptions;

import io.github.amirHFF.errorCode.ErrorCode;
import org.springframework.http.HttpStatus;

public class InUsedException extends ManamanRuntimeException {

    public InUsedException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode.getCode());
        setHttpStatus(HttpStatus.CONFLICT);
    }

    public InUsedException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), errorCode.getCode(), cause);
        setHttpStatus(HttpStatus.CONFLICT);
    }
}
