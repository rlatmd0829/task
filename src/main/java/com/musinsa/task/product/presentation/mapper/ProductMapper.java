package com.musinsa.task.product.presentation.mapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.musinsa.task.product.domain.model.Brand;
import com.musinsa.task.product.domain.model.Category;
import com.musinsa.task.product.domain.model.Product;
import com.musinsa.task.product.presentation.dto.BrandCategoryResponse;
import com.musinsa.task.product.presentation.dto.BrandResponse;
import com.musinsa.task.product.presentation.dto.CategoryPriceResponse;
import com.musinsa.task.product.presentation.dto.CheapestBrandResponse;
import com.musinsa.task.product.presentation.dto.TotalCategoryPriceResponse;

public class ProductMapper {
	public static CategoryPriceResponse toCategoryPriceResponse(Category category, Product product) {
		return new CategoryPriceResponse(
			category.getDisplayName(),
			product.brand().name(),
			product.price().value()
		);
	}

	public static TotalCategoryPriceResponse toTotalPriceResponse(Map<Category, Product> categoryProductMap) {
		List<CategoryPriceResponse> categoryPrices = categoryProductMap.entrySet().stream()
			.sorted(Map.Entry.comparingByKey())
			.map(entry -> toCategoryPriceResponse(entry.getKey(), entry.getValue()))
			.collect(Collectors.toList());
		int total = categoryPrices.stream().mapToInt(CategoryPriceResponse::price).sum();
		return new TotalCategoryPriceResponse(categoryPrices, total);
	}

	public static CheapestBrandResponse toCheapestBrandResponse(Brand brand) {
		List<BrandCategoryResponse> categoryPrices = brand.products().stream()
			.map(product -> new BrandCategoryResponse(product.category().name(), product.price().value()))
			.collect(Collectors.toList());
		int total = brand.getTotalPrice();
		return new CheapestBrandResponse(new BrandResponse(brand.name(), categoryPrices, total));
	}
}
