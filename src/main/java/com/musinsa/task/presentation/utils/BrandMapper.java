package com.musinsa.task.presentation.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.musinsa.task.presentation.dto.request.BrandRequest;
import com.musinsa.task.presentation.dto.request.ProductRequest;
import com.musinsa.task.domain.model.Brand;
import com.musinsa.task.domain.model.Category;
import com.musinsa.task.domain.model.Price;
import com.musinsa.task.domain.model.Product;
import com.musinsa.task.presentation.dto.response.BrandResponse;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BrandMapper {
	public Brand toDomain(BrandRequest brandRequest) {
		List<Product> products = brandRequest.products().stream()
			.map(BrandMapper::toDomain)
			.collect(Collectors.toList());
		return new Brand(null, brandRequest.name(), products);
	}

	public Brand toDomain(Long id, BrandRequest brandRequest) {
		List<Product> products = brandRequest.products().stream()
			.map(BrandMapper::toDomain)
			.collect(Collectors.toList());
		return new Brand(id, brandRequest.name(), products);
	}

	private Product toDomain(ProductRequest productRequest) {
		Category category = Category.fromString(productRequest.categoryName());
		Price price = new Price(productRequest.price());
		return new Product(null, null, category, price); // Brand는 나중에 설정됨
	}

	public BrandResponse toResponse(Brand brand) {
		return new BrandResponse(
			brand.id(),
			brand.name(),
			brand.products().stream()
				.map(product -> new BrandResponse.ProductResponse(
					product.id(),
					product.category().toString(),
					product.price().value()
				))
				.collect(Collectors.toList())
		);
	}
}
