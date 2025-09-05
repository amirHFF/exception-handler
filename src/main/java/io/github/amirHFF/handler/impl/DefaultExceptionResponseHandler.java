package io.github.amirHFF.handler.impl;

import io.github.amirHFF.handler.ExceptionResponseHandler;
import io.github.amirHFF.handler.dto.DefaultResponseDTO;
import io.github.amirHFF.handler.dto.ExceptionDTO;

import java.sql.Timestamp;

public class DefaultExceptionResponseHandler implements ExceptionResponseHandler<DefaultResponseDTO<ExceptionDTO>> {
    @Override
    public DefaultResponseDTO<ExceptionDTO> failureResponse(Object exceptionDTO, String message) {
        if (exceptionDTO instanceof ExceptionDTO) {
            ExceptionDTO exception = (ExceptionDTO) exceptionDTO;
            return new DefaultResponseDTO<>(false, exception, message);
        } else {
            ExceptionDTO manualExceptionDTO = new ExceptionDTO();
            manualExceptionDTO.setErrorCode("?001");
            manualExceptionDTO.setErrorDescription("exception instance mismatch object");
            manualExceptionDTO.setTimestamp(new Timestamp(System.currentTimeMillis()));
            return new DefaultResponseDTO<>(false, manualExceptionDTO, message);
        }
    }
}
