package com.musinsa.task.product.domain.model;

import com.musinsa.task.common.exception.CustomException;
import com.musinsa.task.common.exception.ErrorCode;

public enum Category {
	상의("상의"),
	아우터("아우터"),
	바지("바지"),
	스니커즈("스니커즈"),
	가방("가방"),
	모자("모자"),
	양말("양말"),
	액세서리("액세서리");

	private final String displayName;

	Category(String displayName) {
		this.displayName = displayName;
	}

	public static Category fromString(String categoryName) {
		try {
			return Category.valueOf(categoryName.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new CustomException(ErrorCode.INVALID_TYPE_VALUE);
		}
	}
}
