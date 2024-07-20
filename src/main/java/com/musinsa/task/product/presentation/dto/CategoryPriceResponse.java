package com.musinsa.task.product.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class CategoryPriceResponse {
	private String category;
	private String brand;
	private int price;

	public static CategoryPriceResponse create(String category, String brand, int price) {
		return CategoryPriceResponse.builder()
			.category(category)
			.brand(brand)
			.price(price)
			.build();
	}
}
