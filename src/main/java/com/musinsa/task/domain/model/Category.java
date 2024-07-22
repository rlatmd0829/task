package com.musinsa.task.domain.model;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.musinsa.task.common.exception.CustomException;
import com.musinsa.task.common.exception.ErrorCode;

import lombok.Getter;

@Getter
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

	private static final Map<String, Category> CATEGORY_MAP =
		Arrays.stream(values())
			.collect(Collectors.toMap(c -> c.name().toUpperCase(), Function.identity()));

	public static Category fromString(String categoryName) {
		return Optional.ofNullable(CATEGORY_MAP.get(categoryName.toUpperCase()))
			.orElseThrow(() -> new CustomException(ErrorCode.INVALID_TYPE_VALUE));
	}
}
