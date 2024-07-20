package com.musinsa.task.product.presentation.mapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.musinsa.task.product.domain.model.Category;
import com.musinsa.task.product.domain.model.Product;
import com.musinsa.task.product.presentation.dto.CategoryPriceResponse;
import com.musinsa.task.product.presentation.dto.TotalPriceResponse;

public class ProductMapper {
	public static CategoryPriceResponse toCategoryPriceResponse(Category category, Product product) {
		return new CategoryPriceResponse(
			category.getDisplayName(),
			product.brand().name(),
			product.price().value()
		);
	}

	public static TotalPriceResponse toTotalPriceResponse(Map<Category, Product> categoryProductMap) {
		List<CategoryPriceResponse> categoryPrices = categoryProductMap.entrySet().stream()
			.sorted(Map.Entry.comparingByKey())
			.map(entry -> toCategoryPriceResponse(entry.getKey(), entry.getValue()))
			.collect(Collectors.toList());
		int total = categoryPrices.stream().mapToInt(CategoryPriceResponse::price).sum();
		return new TotalPriceResponse(categoryPrices, total);
	}
}
