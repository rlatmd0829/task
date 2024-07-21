package com.musinsa.task.common.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
	T data;
	int code;
	String message;

	public ApiResponse() {
		this.code = ErrorCode.SUCCESS.getHttpStatus().value();
		this.message = ErrorCode.SUCCESS.getMessage();
	}

	public ApiResponse(T data) {
		this.data = data;
		this.code = ErrorCode.SUCCESS.getHttpStatus().value();
		this.message = ErrorCode.SUCCESS.getMessage();
	}
}
