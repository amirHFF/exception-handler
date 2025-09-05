package io.github.amirHFF.handler.dto;

import org.apache.logging.log4j.ThreadContext;

public class DefaultResponseDTO<T> {
    private String referenceNumber = ThreadContext.get("referenceNumber");
    private boolean isSuccessful;
    private T content;
    private String message;

    public DefaultResponseDTO() {
    }

    public DefaultResponseDTO(boolean isSuccessful, T content) {
        this.isSuccessful = isSuccessful;
        this.content = content;
    }

    public DefaultResponseDTO(boolean isSuccessful, T content, String message) {
        this.isSuccessful = isSuccessful;
        this.content = content;
        this.message = message;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
