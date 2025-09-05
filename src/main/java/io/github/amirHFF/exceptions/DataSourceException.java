package io.github.amirHFF.exceptions;

import io.github.amirHFF.errorCode.ErrorCode;
import org.springframework.http.HttpStatus;

public abstract class DataSourceException extends ManamanRuntimeException {
    private String monitoringMessage;

    public DataSourceException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), errorCode.getCode(), cause);
        setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public String getMonitoringMessage() {
        return monitoringMessage;
    }

    public void setMonitoringMessage(String monitoringMessage) {
        this.monitoringMessage = monitoringMessage;
    }
}
