package com.musinsa.task.domain.model;

import com.musinsa.task.common.exception.CustomException;
import com.musinsa.task.common.exception.ErrorCode;

public record Price(int value) {

	public Price {
		if (value < 0) {
			throw new CustomException(ErrorCode.INVALID_INPUT_VALUE);
		}
	}
}
