package com.musinsa.task.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {
	SUCCESS(HttpStatus.OK, "complete"),
	/* 400 BAD_REQUEST */
	INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "잘못된 입력값 입니다."),
	INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "잘못된 타입입니다."),

	/* 404 NOT_FOUND */
	PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "상품을 찾을 수 없습니다."),
	BRAND_NOT_FOUND(HttpStatus.NOT_FOUND, "브랜드를 찾을 수 없습니다."),

	/* 500 INTERNAL_SERVER_ERROR */
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 오류가 발생했습니다.");

	private final HttpStatus httpStatus;
	private final String message;

	ErrorCode(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

}
