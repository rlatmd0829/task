package com.musinsa.task.product.presentation.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class TotalPriceResponse {
	private List<CategoryPriceResponse> categoryPrices;
	private int total;

	public static TotalPriceResponse create(List<CategoryPriceResponse> categoryPrices, int total) {
		return TotalPriceResponse.builder()
			.categoryPrices(categoryPrices)
			.total(total)
			.build();
	}
}
