package io.github.amirHFF.exceptions;

import io.github.amirHFF.errorCode.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

public class RestException extends ManamanRuntimeException {
    private String restMessage;
    private String destinationName = "null";
    private Type type;

    public RestException(ErrorCode errorCode, Throwable cause, HttpStatus httpStatus) {
        super(errorCode, httpStatus, cause);
        if (cause.getCause() instanceof ResourceAccessException) {
            type = Type.RESOURCE_ACCESS_EXCEPTION;
            setHttpStatus(HttpStatus.SERVICE_UNAVAILABLE);
        }
        if (cause.getCause() instanceof HttpServerErrorException.InternalServerError) {
            type = Type.INTERNAL_SERVER_ERROR;
            setHttpStatus(HttpStatus.SERVICE_UNAVAILABLE);
        }
        restMessage = cause.getMessage();
    }
    public RestException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, HttpStatus.SERVICE_UNAVAILABLE, cause);
        if (cause.getCause() instanceof ResourceAccessException) {
            type = Type.RESOURCE_ACCESS_EXCEPTION;
        }
        if (cause.getCause() instanceof HttpServerErrorException.InternalServerError) {
            type = Type.INTERNAL_SERVER_ERROR;
            setHttpStatus(HttpStatus.SERVICE_UNAVAILABLE);
        }
        restMessage = cause.getMessage();
    }

    public String getMonitoringAlarm() {
        return "!! MONITORING ALARM !! : request to " + destinationName + " caused " + type.getValue();
    }

    public String getRestMessage() {
        return restMessage;
    }

    public void setRestMessage(String restMessage) {
        this.restMessage = restMessage;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public enum Type {
        RESOURCE_ACCESS_EXCEPTION("I/O exception"),
        INTERNAL_SERVER_ERROR("Internal Server Error");

        private final String value;

        Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }
}
