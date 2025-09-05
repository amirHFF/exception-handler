package io.github.amirHFF.exceptions;

import io.github.amirHFF.errorCode.ErrorCode;
import io.github.amirHFF.errorCode.ErrorCodeBuilder;
import org.springframework.http.HttpStatus;

public class ManamanRuntimeException extends RuntimeException {
    private HttpStatus httpStatus;
    private String errorCode;

    public ManamanRuntimeException(String message) {
        super(message);
    }

    public ManamanRuntimeException(String message, String errorCode) {
        super(message);
        this.errorCode = ErrorCodeBuilder.build(errorCode);
    }

    public ManamanRuntimeException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = ErrorCodeBuilder.build(errorCode);
    }

    public ManamanRuntimeException(ErrorCode errorCode, HttpStatus httpStatus, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode.getCode();
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    protected void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
