package io.github.amirHFF.handler.dto;


import io.github.amirHFF.exceptions.ManamanRuntimeException;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

public class ExceptionDTO {
    private String errorCode;
    private String errorUri;
    private Timestamp timestamp;
    private String errorDescription;

    public ExceptionDTO() {
    }

    public ExceptionDTO(ManamanRuntimeException exception, HttpServletRequest request) {
        errorCode = exception.getErrorCode();
        errorDescription = exception.getMessage();
        errorUri = request.getRequestURL().toString();
        timestamp = new Timestamp(System.currentTimeMillis());
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorUri() {
        return errorUri;
    }

    public void setErrorUri(String errorUri) {
        this.errorUri = errorUri;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

}
