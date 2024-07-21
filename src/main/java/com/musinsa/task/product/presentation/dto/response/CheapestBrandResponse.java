package com.musinsa.task.product.presentation.dto.response;

import java.util.List;

public record CheapestBrandResponse(BrandResponse cheapest) {
	public record BrandResponse(String brand, List<BrandCategoryPriceResponse> categories, int total) {
	}

	public record BrandCategoryPriceResponse(String category, int price) {
	}
}
