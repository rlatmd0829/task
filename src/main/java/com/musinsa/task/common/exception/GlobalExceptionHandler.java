package com.musinsa.task.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomException.class)
	protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e, HttpServletRequest request) {
		log.error("CustomException: {}", e.getMessage(), e);
		return ErrorResponse.toResponseEntity(e.getErrorCode(), e.getErrorCode().getMessage(), request);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
		log.error("MethodArgumentNotValidException: {}", e.getMessage(), e);
		return ErrorResponse.toResponseEntity(ErrorCode.INVALID_INPUT_VALUE, ErrorCode.INVALID_INPUT_VALUE.getMessage(), request);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
		log.error("ConstraintViolationException: {}", e.getMessage(), e);
		return ErrorResponse.toResponseEntity(ErrorCode.INVALID_INPUT_VALUE, ErrorCode.INVALID_INPUT_VALUE.getMessage(), request);
	}
}
