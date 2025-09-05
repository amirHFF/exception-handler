package io.github.amirHFF.handler;

import io.github.amirHFF.errorCode.ErrorCodeBuilder;
import io.github.amirHFF.errorCode.GlobalErrorCode;
import io.github.amirHFF.handler.dto.ExceptionDTO;
import io.github.amirHFF.handler.impl.DefaultExceptionResponseHandler;
import io.github.amirHFF.exceptions.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import java.sql.Timestamp;

@ControllerAdvice
public class GlobalExceptionsHandler {

	private final Logger logger = LogManager.getLogger(GlobalExceptionsHandler.class);
	private ExceptionResponseHandler responseHandler = new DefaultExceptionResponseHandler();

	protected void setResponseHandlerInstance(ExceptionResponseHandler<Object> responseHandler) {
		this.responseHandler = responseHandler;
	}

	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<Object> unHandledExceptionHandler(Throwable throwable, WebRequest webRequest) {

		ExceptionDTO exceptionDTO = new ExceptionDTO();
		exceptionDTO.setErrorUri(((ServletWebRequest) webRequest).getRequest().getRequestURL().toString());
		exceptionDTO.setErrorCode(ErrorCodeBuilder.build("9999"));
		exceptionDTO.setErrorDescription(throwable.getMessage());
		exceptionDTO.setTimestamp(new Timestamp(System.currentTimeMillis()));

		logger.error(throwable.getMessage(), throwable);
		return ResponseEntity.internalServerError().body(responseHandler.failureResponse(exceptionDTO, "a dangerous unhandled exception threw during regular process , please call to support team"));
	}

	@ExceptionHandler(value = RestClientException.class)
	public ResponseEntity<Object> handleRestClientException(Throwable throwable, WebRequest webRequest) {

		int statusCode = 500;
		if (throwable.getCause() instanceof RestClientResponseException) {
			statusCode = ((RestClientResponseException) throwable).getRawStatusCode();
		}
		RestClientException exception = (RestClientException) throwable;
		ExceptionDTO exceptionDTO = new ExceptionDTO();

		exceptionDTO.setErrorCode(ErrorCodeBuilder.build("9997"));

		exceptionDTO.setErrorUri(((ServletWebRequest) webRequest).getRequest().getRequestURL().toString());
		exceptionDTO.setTimestamp(new Timestamp(System.currentTimeMillis()));
		exceptionDTO.setErrorDescription(exception.getMessage());

		logger.error(exceptionDTO.getErrorDescription());
		logger.error(throwable.getMessage(), throwable);
		return ResponseEntity.status(HttpStatus.valueOf(statusCode)).body(responseHandler.failureResponse(exceptionDTO, "an exception occurred during rest services"));
	}

	@ExceptionHandler(RestException.class)
	public ResponseEntity<Object> handleRestException(Throwable throwable, WebRequest webRequest) {

		RestException exception = (RestException) throwable;
		ExceptionDTO exceptionDTO = new ExceptionDTO(exception, ((ServletWebRequest) webRequest).getRequest());
		logger.error(throwable.getMessage(), throwable);
		logger.error("specific rest exception : {}", exception.getRestMessage());
		if (exception.getType() != null) {
			logger.error(exception.getMonitoringAlarm());
		}
		return ResponseEntity.status(exception.getHttpStatus()).body(responseHandler.failureResponse(exceptionDTO, "an external rest service encountered an error"));
	}

	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<Object> handleNotFoundException(Throwable throwable, WebRequest webRequest) {

		NotFoundException exception = (NotFoundException) throwable;
		ExceptionDTO exceptionDTO = new ExceptionDTO(exception, ((ServletWebRequest) webRequest).getRequest());
		logger.error(throwable.getMessage(), throwable);
		return ResponseEntity.status(exception.getHttpStatus()).body(responseHandler.failureResponse(exceptionDTO, "not found exception occurred"));
	}

	@ExceptionHandler(value = BadInputException.class)
	public ResponseEntity<Object> handleBadInputException(Throwable throwable, WebRequest webRequest) {

		BadInputException exception = (BadInputException) throwable;
		ExceptionDTO exceptionDTO = new ExceptionDTO(exception, ((ServletWebRequest) webRequest).getRequest());
		logger.error(throwable.getMessage(), throwable);

		return ResponseEntity.status(exception.getHttpStatus()).body(responseHandler.failureResponse(exceptionDTO, "bad input exception threw , this mean a input parameter has problem"));
	}

	@ExceptionHandler(value = DataSourceException.class)
	public ResponseEntity<Object> handleDataSourceException(Throwable throwable, WebRequest webRequest) {

		DataSourceException exception = (DataSourceException) throwable;
		ExceptionDTO exceptionDTO = new ExceptionDTO(exception, ((ServletWebRequest) webRequest).getRequest());
		logger.error(throwable.getMessage(), throwable);
		logger.error("monitoring alarm : {}", exception.getMonitoringMessage());

		return ResponseEntity.status(exception.getHttpStatus()).body(responseHandler.failureResponse(exceptionDTO, "an exception threw from database"));
	}

	@ExceptionHandler(value = InUsedException.class)
	public ResponseEntity<Object> handleInUsedException(Throwable throwable, WebRequest webRequest) {

		InUsedException exception = (InUsedException) throwable;
		ExceptionDTO exceptionDTO = new ExceptionDTO(exception, ((ServletWebRequest) webRequest).getRequest());
		logger.error(throwable.getMessage(), throwable);

		return ResponseEntity.status(exception.getHttpStatus()).body(responseHandler.failureResponse(exceptionDTO, "inUsed exception occurred"));
	}

	@ExceptionHandler(value = DuplicateException.class)
	public ResponseEntity<Object> handleDuplicateException(Throwable throwable, WebRequest webRequest) {

		DuplicateException exception = (DuplicateException) throwable;
		ExceptionDTO exceptionDTO = new ExceptionDTO(exception, ((ServletWebRequest) webRequest).getRequest());
		logger.error(throwable.getMessage(), throwable);

		return ResponseEntity.status(exception.getHttpStatus()).body(responseHandler.failureResponse(exceptionDTO, "duplicate exception occurred"));

	}

	@ExceptionHandler(value = AccessLevelException.class)
	public ResponseEntity<Object> handleAccessLevelException(Throwable throwable, WebRequest webRequest) {

		AccessLevelException exception = (AccessLevelException) throwable;
		ExceptionDTO exceptionDTO = new ExceptionDTO(exception, ((ServletWebRequest) webRequest).getRequest());
		logger.error(throwable.getMessage(), throwable);

		return ResponseEntity.status(exception.getHttpStatus()).body(responseHandler.failureResponse(exceptionDTO, "an access exception occurred , there is no access to this resource"));
	}

	@ExceptionHandler(value = BusinessException.class)
	public ResponseEntity<Object> handleBusinessException(Throwable throwable, WebRequest webRequest) {

		BusinessException exception = (BusinessException) throwable;
		ExceptionDTO exceptionDTO = new ExceptionDTO(exception, ((ServletWebRequest) webRequest).getRequest());
		logger.error(throwable.getMessage(), throwable);

		return ResponseEntity.status(exception.getHttpStatus()).body(responseHandler.failureResponse(exceptionDTO, "business exception occurred"));
	}

	@ExceptionHandler(value = BadJsonException.class)
	public ResponseEntity<Object> handleBadJsonException(Throwable throwable, WebRequest webRequest) {

		BadJsonException exception = (BadJsonException) throwable;
		ExceptionDTO exceptionDTO = new ExceptionDTO(exception, ((ServletWebRequest) webRequest).getRequest());
		logger.error(throwable.getMessage(), throwable);

		return ResponseEntity.status(exception.getHttpStatus()).body(responseHandler.failureResponse(exceptionDTO, "wrong json syntax exception occurred"));
	}

	@ExceptionHandler(value = NullPointerException.class)
	public ResponseEntity<Object> handleNullPointerException(Throwable throwable, WebRequest webRequest) {

		ExceptionDTO exceptionDTO = new ExceptionDTO();
		exceptionDTO.setErrorUri(((ServletWebRequest) webRequest).getRequest().getRequestURL().toString());
		exceptionDTO.setErrorCode("9998");
		exceptionDTO.setErrorDescription("null pointer exception");
		exceptionDTO.setTimestamp(new Timestamp(System.currentTimeMillis()));

		logger.error(throwable.getMessage(), throwable);
		return ResponseEntity.internalServerError().body(responseHandler.failureResponse(exceptionDTO, "a dangerous unhandled null pointer exception threw during regular process , please call to support team"));
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Object> argumentNotValidExceptionHandler(Throwable throwable, WebRequest webRequest) {

		MethodArgumentNotValidException exception = (MethodArgumentNotValidException) throwable;
		ExceptionDTO exceptionDTO = new ExceptionDTO();
		exceptionDTO.setErrorCode(GlobalErrorCode.BAD_INPUT_PARAMETER_VALIDATION.getCode());
		exceptionDTO.setErrorUri(((ServletWebRequest) webRequest).getRequest().getRequestURL().toString());
		exceptionDTO.setTimestamp(new Timestamp(System.currentTimeMillis()));
		if (exception.getErrorCount() > 0) {
			exceptionDTO.setErrorDescription("");
		} else {
			exceptionDTO.setErrorDescription(exception.getMessage());
		}
		for (ObjectError objectError : exception.getAllErrors()) {
			if (objectError.getDefaultMessage() != null) {
				exceptionDTO.setErrorDescription(exceptionDTO.getErrorDescription().concat(objectError.getDefaultMessage()).concat(","));
			} else {
				exceptionDTO.setErrorDescription(exceptionDTO.getErrorDescription().concat(" , ").concat("there is a null message problem on " + objectError.getObjectName()));
			}
		}
		logger.error(exceptionDTO.getErrorDescription());
		logger.error(throwable.getMessage(), throwable);
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(responseHandler.failureResponse(exceptionDTO, "constraint violation occurred"));
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Object> constraintViolationExceptionHandler(Throwable throwable, WebRequest webRequest) {

		ExceptionDTO exceptionDTO = new ExceptionDTO();
		exceptionDTO.setErrorUri(((ServletWebRequest) webRequest).getRequest().getRequestURL().toString());
		exceptionDTO.setTimestamp(new Timestamp(System.currentTimeMillis()));
		if (throwable.getCause() instanceof ConstraintViolationException) {
			String constraintName = ((ConstraintViolationException) throwable.getCause()).getConstraintName();
			exceptionDTO.setErrorDescription("could not execute statement SQL because of constraint of [" + constraintName + "]");

		} else {
			exceptionDTO.setErrorDescription(throwable.getMessage());
		}
		exceptionDTO.setErrorCode("9995");
		logger.error(exceptionDTO.getErrorDescription());
		logger.error(throwable.getMessage(), throwable);
		return ResponseEntity.internalServerError().body(responseHandler.failureResponse(exceptionDTO, "constraint violation occurred"));
	}
}