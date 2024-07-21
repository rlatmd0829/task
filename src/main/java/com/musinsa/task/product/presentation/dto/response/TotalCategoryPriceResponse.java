package com.musinsa.task.product.presentation.dto.response;

import java.util.List;

public record TotalCategoryPriceResponse(List<CategoryPrice> categoryPrices, int total) {
	public record CategoryPrice(String category, String brand, int price) {
	}
}
