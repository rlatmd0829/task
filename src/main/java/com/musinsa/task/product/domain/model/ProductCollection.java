package com.musinsa.task.product.domain.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.musinsa.task.common.exception.CustomException;
import com.musinsa.task.common.exception.ErrorCode;

public record ProductCollection(List<Product> products) {
	public Map<Category, Product> findCheapestProductsByCategory() {
		return groupByCategory().entrySet().stream()
			.collect(Collectors.toMap(
				Map.Entry::getKey,
				entry -> findCheapestProduct(entry.getValue())
			));
	}

	public Map<Category, List<Product>> groupByCategory() {
		return products.stream()
			.collect(Collectors.groupingBy(Product::category));
	}

	public Product findCheapestProduct(List<Product> products) {
		return products.stream()
			.min(Comparator.comparingInt(p -> p.price().value()))
			.orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
	}

	public Brand findCheapestBrand() {
		Map<Brand, List<Product>> brandProductsMap = products.stream()
			.collect(Collectors.groupingBy(Product::brand, HashMap::new, Collectors.toList()));

		brandProductsMap.forEach(Brand::setProducts);

		return brandProductsMap.keySet().stream()
			.min(Comparator.comparingInt(Brand::getTotalPrice))
			.orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
	}
}
