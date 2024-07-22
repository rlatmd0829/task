package com.musinsa.task.unit.domain.model;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import com.musinsa.task.common.exception.CustomException;
import com.musinsa.task.common.exception.ErrorCode;
import com.musinsa.task.domain.model.Category;

class CategoryTest {
	@ParameterizedTest
	@EnumSource(Category.class)
	@DisplayName("모든 유효한 카테고리 테스트")
	void testValidCategory(Category category) {
		assertEquals(category, Category.fromString(category.getDisplayName()));
	}

	@Test
	void testInvalidCategory() {
		assertThatThrownBy(() -> Category.fromString("장난감"))
			.isInstanceOf(CustomException.class)
			.hasMessage(ErrorCode.INVALID_TYPE_VALUE.getMessage());
	}
}
