package com.musinsa.task.product.presentation.mapper;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.musinsa.task.product.application.dto.PriceInfo;
import com.musinsa.task.product.domain.model.Brand;
import com.musinsa.task.product.domain.model.Category;
import com.musinsa.task.product.domain.model.Product;
import com.musinsa.task.product.presentation.dto.CheapestBrandResponse;
import com.musinsa.task.product.presentation.dto.PriceInfoResponse;
import com.musinsa.task.product.presentation.dto.TotalCategoryPriceResponse;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductMapper {
	public static TotalCategoryPriceResponse toTotalPriceResponse(Map<Category, Product> categoryProductMap) {
		List<TotalCategoryPriceResponse.CategoryPrice> categoryPrices = categoryProductMap.entrySet().stream()
			.map(entry -> new TotalCategoryPriceResponse.CategoryPrice(
				entry.getKey().name(),
				entry.getValue().brand().name(),
				entry.getValue().price().value()
			))
			.sorted(Comparator.comparingInt(entry -> Category.valueOf(entry.category()).ordinal()))
			.collect(Collectors.toList());

		int total = categoryPrices.stream().mapToInt(TotalCategoryPriceResponse.CategoryPrice::price).sum();

		return new TotalCategoryPriceResponse(categoryPrices, total);
	}

	public static CheapestBrandResponse toCheapestBrandResponse(Brand brand) {
		List<CheapestBrandResponse.BrandCategoryPriceResponse> categoryPrices = brand.products().stream()
			.map(product -> new CheapestBrandResponse.BrandCategoryPriceResponse(product.category().name(), product.price().value()))
			.collect(Collectors.toList());
		int total = brand.getTotalPrice();
		return new CheapestBrandResponse(
			new CheapestBrandResponse.BrandResponse(brand.name(), categoryPrices, total)
		);
	}

	public static PriceInfoResponse toPriceInfoResponse(PriceInfo priceInfo) {
		return new PriceInfoResponse(
			priceInfo.category(),
			new PriceInfoResponse.PriceDetail(priceInfo.minPriceBrand(), priceInfo.minPrice()),
			new PriceInfoResponse.PriceDetail(priceInfo.maxPriceBrand(), priceInfo.maxPrice())
		);
	}
}
