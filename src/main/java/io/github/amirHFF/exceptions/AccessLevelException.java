package io.github.amirHFF.exceptions;

import io.github.amirHFF.errorCode.ErrorCode;
import org.springframework.http.HttpStatus;

public class AccessLevelException extends ManamanRuntimeException {

    public AccessLevelException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode.getCode());
        setHttpStatus(HttpStatus.FORBIDDEN);
    }



    public AccessLevelException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), errorCode.getCode(), cause);
        setHttpStatus(HttpStatus.FORBIDDEN);
    }
}
