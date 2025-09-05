package io.github.amirHFF.handler;

public interface ExceptionResponseHandler<R> {
    R failureResponse(Object exceptionDTO, String message);
}
