package com.fastcampus.mini9.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fastcampus.mini9.common.response.ErrorResponseBody;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponseBody> handleException(MethodArgumentNotValidException ex) {
		logger.warn("invalid request", ex);
		String errorMessage = ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
		return ResponseEntity
			.badRequest()
			.body(new ErrorResponseBody(false, 400, errorMessage));
	}

	@ExceptionHandler(BaseException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponseBody> handleException(Exception ex) {
		logger.error("error occur!", ex);
		return ResponseEntity
			.internalServerError()
			.body(new ErrorResponseBody(false, 500, ex.getMessage()));
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponseBody> handleException(MethodArgumentTypeMismatchException ex) {
		logger.warn("invalid request", ex);
		String errorMessage = ex.getName() + "을 양식에 맞게 다시 입력해주세요.";
		return ResponseEntity
			.badRequest()
			.body(new ErrorResponseBody(false, 400, errorMessage));
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponseBody> handleException(ConstraintViolationException ex) {
		logger.warn("Validation error", ex);
		String errorMessage = ex.getMessage();
		return ResponseEntity
			.badRequest()
			.body(new ErrorResponseBody(false, 400, errorMessage));
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponseBody> handleException(IllegalArgumentException ex) {
		logger.warn("Invalid argument", ex);
		String errorMessage = ex.getMessage();
		return ResponseEntity
			.badRequest()
			.body(new ErrorResponseBody(false, 400, errorMessage));
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ErrorResponseBody> handleException(MissingServletRequestParameterException ex) {
		String errorMessage = ex.getParameterName() + " 를 필수로 입력해주셔야 합니다.";
		return ResponseEntity
			.badRequest()
			.body(new ErrorResponseBody(false, 400, errorMessage));
	}
}
