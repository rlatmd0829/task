package com.musinsa.task.presentation.utils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.musinsa.task.application.dto.PriceInfo;
import com.musinsa.task.domain.model.Brand;
import com.musinsa.task.domain.model.Category;
import com.musinsa.task.domain.model.Product;
import com.musinsa.task.presentation.dto.response.CheapestBrandResponse;
import com.musinsa.task.presentation.dto.response.PriceInfoResponse;
import com.musinsa.task.presentation.dto.response.TotalCategoryPriceResponse;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductMapper {
	public TotalCategoryPriceResponse toTotalPriceResponse(Map<Category, Product> categoryProductMap) {
		List<TotalCategoryPriceResponse.CategoryPriceDetail> categoryPriceDetails = categoryProductMap.entrySet().stream()
			.map(entry -> new TotalCategoryPriceResponse.CategoryPriceDetail(
				entry.getKey().name(),
				entry.getValue().brand().name(),
				entry.getValue().price().value()
			))
			.sorted(Comparator.comparingInt(entry -> Category.valueOf(entry.category()).ordinal()))
			.collect(Collectors.toList());

		int total = categoryPriceDetails.stream().mapToInt(TotalCategoryPriceResponse.CategoryPriceDetail::price).sum();

		return new TotalCategoryPriceResponse(categoryPriceDetails, total);
	}

	public CheapestBrandResponse toCheapestBrandResponse(Brand brand) {
		List<CheapestBrandResponse.CategoryPriceDetail> categoryPrices = brand.products().stream()
			.map(product -> new CheapestBrandResponse.CategoryPriceDetail(product.category().name(), product.price().value()))
			.collect(Collectors.toList());
		int total = brand.getTotalPrice();
		return new CheapestBrandResponse(
			new CheapestBrandResponse.CheapestBrandDetail(brand.name(), categoryPrices, total)
		);
	}

	public PriceInfoResponse toPriceInfoResponse(PriceInfo priceInfo) {
		return new PriceInfoResponse(
			priceInfo.category(),
			new PriceInfoResponse.PriceDetail(priceInfo.minPriceBrand(), priceInfo.minPrice()),
			new PriceInfoResponse.PriceDetail(priceInfo.maxPriceBrand(), priceInfo.maxPrice())
		);
	}
}
