package com.musinsa.task.product.domain.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.musinsa.task.common.exception.CustomException;
import com.musinsa.task.common.exception.ErrorCode;

import lombok.Getter;

@Getter
public class ProductCollection {
	private final List<Product> products;

	public ProductCollection(List<Product> products) {
		this.products = Collections.unmodifiableList(products);
	}

	public Map<Category, Product> findCheapestProductsByCategory() {
		return groupByCategory().entrySet().stream()
			.collect(Collectors.toMap(
				Map.Entry::getKey,
				entry -> findCheapestProduct(entry.getValue())
			));
	}

	public Map<Category, List<Product>> groupByCategory() {
		return products.stream()
			.collect(Collectors.groupingBy(Product::getCategory));
	}

	public Product findCheapestProduct(List<Product> products) {
		return products.stream()
			.min(Comparator.comparingInt(p -> p.getPrice().getValue()))
			.orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
	}
}
