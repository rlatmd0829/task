package com.musinsa.task.product.domain.model;

import com.musinsa.task.common.exception.CustomException;
import com.musinsa.task.common.exception.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Price {

	private final int value;

	private Price(int value) {
		if (value < 0) {
			throw new CustomException(ErrorCode.INVALID_INPUT_VALUE);
		}
		this.value = value;
	}

	public static Price create(int value) {
		return Price.builder()
			.value(value)
			.build();
	}

	public int getValue() {
		return value;
	}
}
