package com.musinsa.task.unit.product.domain.model;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.musinsa.task.common.exception.CustomException;
import com.musinsa.task.common.exception.ErrorCode;
import com.musinsa.task.product.domain.model.Price;

class PriceTest {

	@Test
	@DisplayName("가격 생성 테스트")
	void createPriceTest() {
		Price price = new Price(10000);
		assertNotNull(price);
	}

	@Test
	@DisplayName("가격 생성 실패 테스트")
	void createPriceFailTest() {
		assertThatThrownBy(() -> new Price(-10000))
			.isInstanceOf(CustomException.class)
			.hasMessage(ErrorCode.INVALID_INPUT_VALUE.getMessage());
	}
}
